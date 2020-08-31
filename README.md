# MustangMinibots
Code for Team 670's 2020 OffSeason Workshops' MustangMinibots

## Repository Organization
- MiniLib: Java code and files to be deployed to run on the RoboRIO that are organized and deployed with GradleRIO
- Minibot: Basic Java code and files to be deployed to run on the RoboRIO 
- NetworkTables: Java code and files that act as a database and client with the server running on the RoboRio
- Testers: Jar file to test encoders

## Branching and Workflow on this Repository
[Please check this document for the team's policy for committing code to GitHub!](https://docs.google.com/document/d/1vO_dtVTDw3-l0x0BabiAE5C45O6bJlQeLL1Uy9McOcQ/edit)

Note that you cannot commit directly to master or dev!

This project shall follow the following workflow:

The master branch is considered the stable branch of this project. It may only be updated via pull request from a student developer, and then only with the Code Leads’ approval.

The dev branch is the main working branch. It may only be updated by pull request from uncontrolled branches.

For regular development each developer shall create a "feature branch" this is a branch named in the convention: "feature/name" or "bugfix/name". These are for new features and for bugfixes, respectively.

When work starts on a new feature, its branch will be made off of the latest version of dev, and all development will occur on the branch. When the feature is considered ready, it will be merged onto the dev branch. When merging, automatic merging, LV Merge tool merging, or simply copying and pasting of code fragments may be necessary.

## Pi Setup
```
sudo apt-get update
git clone https://github.com/HHS-Team670/MustangMinibots
cd MustangMinibots/PIGPIO
make
sudo make install
```



