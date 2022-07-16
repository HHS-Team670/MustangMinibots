#!/usr/bin/env bash

# 670 Minibot Setup script
# Input the *single-digit* bot number as the first arg

# exit on error
set -e

if [ $# -ne 1 ]; then
  echo "Usage: $0 bot-number"
  echo "bot-number is a single digit"
  exit 1
fi

BOT_NUMBER=$1

# Self-elevate
if [ "$(whoami)" != 'root' ]; then
  echo 'This script must be run as root.'
  echo 'Enter password to elevate privileges:'
  SCRIPTPATH=$( cd $(dirname $0) ; pwd -P )
  SELF=`basename $0`
  sudo $SCRIPTPATH'/'$SELF
  exit 1
fi
echo 'Script running with root privileges.'

# Change SSH password
echo 'pi:hhs670' | chpasswd

# Enable ssh (will go into effect on reboot)
touch /boot/ssh

# Wifi AP setup

# Install the package
apt install -y hostapd dnsmasq
systemctl unmask hostapd
systemctl enable hostapd

DEBIAN_FRONTEND=noninteractive apt install -y netfilter-persistent iptables-persistent

cat >> /etc/dhcpd.conf << EOF
interface wlan0
    static ip_address=192.168.$BOT_NUMBER.1/24
    nohook wpa_supplicant
EOF

mv /etc/dnsmasq.conf /etc/dnsmasq.conf.orig

cat > /etc/dnsmasq.conf << EOF
interface=wlan0 # Listening interface
dhcp-range=192.168.$BOT_NUMBER.2,192.168.$BOT_NUMBER.20,255.255.255.0,24h
                # Pool of IP addresses served via DHCP
domain=wlan     # Local wireless DNS domain
address=/gw.wlan/192.168.$BOT_NUMBER.1
                # Alias for this router
EOF

rfkill unblock wlan

cat > /etc/hostapd/hostapd.conf << EOF
country_code=US
interface=wlan0
ssid=HHS_Minibot0$BOT_NUMBER
hw_mode=g
channel=7
macaddr_acl=0
auth_algs=1
ignore_broadcast_ssid=0
wpa=2
wpa_passphrase=hhs6700$BOT_NUMBER
wpa_key_mgmt=WPA-PSK
wpa_pairwise=TKIP
rsn_pairwise=CCMP
EOF

# now we change the MOTD to get the user to run the install script
cp /etc/motd /etc/motd.bak
cat >> /etc/motd << EOF
!!!!!!!!!!!!!!!!!!!!
Please run the installer.sh script in the minibot repo
to finish setting up.
!!!!!!!!!!!!!!!!!!!!
EOF

echo "Done!"
# this is from https://stackoverflow.com/a/226724
while true; do
    read -p "A reboot is required. Do it now? " yn
    case $yn in
        [Yy]* ) reboot;;
        [Nn]* ) echo "Please reboot later." exit;;
        * ) echo "Please answer yes or no.";;
    esac
done
