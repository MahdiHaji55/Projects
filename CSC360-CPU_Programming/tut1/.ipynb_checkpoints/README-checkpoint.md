# CSC360 Tutorial 1

## Getting started

Welcome to the first CSC360 tutorial.  Most of these tutorials will focus on assignment support, but this first tutorial will allow you to familiarize yourself with the tools we'll be using in this course.  Tutorials are not graded, so feel free to experiment.

## GitLab

The code for our tutorials and assignments can be found at https://gitlab.csc.uvic.ca .  You have a repository there, at:

`gitlab.csc.uvic.ca/courses/2024011/CSC360_COSI/assignments/<YOUR NETLINK ID>/coursework-os` 

Go there now and take a look - most of the folders are empty, but the
`tut1/` folder has some files in it (including this file, `README.md`).

## JupyterLab

Our programming environment for the course will be JupyterLab, located at `https://jhub-cosi.uvic.ca/` 

The only tools we'll need to use for the course are the **Terminal** and the **Text File** buttons, in the Other section at the bottom of the launcher.  

## Configure Git

Open a terminal and take a look around the directory structure.  At this point there probably isn't much there, unless you've used Jupyter for another class.  You may wish to create a directory just for this course.

Tell Git who you are:

```
git config --global user.name <YOUR NETLINK ID>
git config --global user.email <YOUR EMAIL ADDRESS>
```

## Clone your repository

Locate the CLONE button in GitLab for the course repository, and copy the url under the **Clone with HTTPS** option.  Clone your repo into Jupyter with the `git clone` command.  You will probably need to 

## Challenge 1:

Write a simple hello-world program.  Call it `hello_world.c`, and compile and run it to make sure it works.  To make sure there are no syntax errors or other problems, compile it with warning flags set:

`gcc -Wall -Werror -std=c18 -o hello_world hello_world.c`

Once you have it working, use `git add` to set your new file to be tracked by git.  Write a `git commit` message (make it something descriptive) and then `git push` your changes back to GitLab.

Make sure your push worked!  Go to GitLab in a web browser and check that your new file(s) are there.  GitLab has also been configured to send you an email to your `uvic.ca` e-mail when you push your repository, so make sure you've received this confirmation email.

Remember we can't mark your work if it's not in GitLab, so get used to this commit-and-push procedure.

## Challenge 2:

In your `tut1/` directory, you'll find a text file called `input_text.txt`.  Open it and look it over - it describes the task you need to do (You need to write a short program that replicates the behaviour of the `wc -w` unix utility).

Give this program a try!  If you find yourself having a lot of trouble remembering your C syntax, you might want to set aside some time to refresh your knowledge before the first assignment.

If you try Challenge 2, remember to commit and push your changes!

## If things go wrong:

You may accidently write an infinite loop or otherwise use up all the resources available to you, and you may find that the UI becomes unresponsive.  It's important that we're able to recover from these sorts of situations, which often arise when you start playing with fundamental parts of the operating system.

Remember that you can stop a running program using **CTRL-c**.

If things are slowing down too much, you may have a terminal or kernel that has become starved for resources.  You can close down a terminal or kernel by clicking the 'square-in-a-circle' button on the left edge of the screen, and cloosing 'Shut down' or 'Shut down all' from the Kernels or Terminals list, depending on the situation.

## Caching your Git credentials

Sometimes it's irritating to type your username and password each time y`ou interact with GitLab.  You may choose to get git to remember your credentials for a short time.  If you want to cache your credentials for 15 minutes, use the default command:

`git config --global credential.helper cache`

You may want to do so for longer than the default period; the following command will set the timeout to one hour:

`git config --global credential.helper "cache --timeout=3600"`
