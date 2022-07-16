# Now to install it!
set -e
 
sudo cp /etc/motd.bak /etc/motd


unzip PIGPIO.zip
cd PIGPIO
make
sudo make install

echo "Done! Try copying and running a jar now."
