# CS5004 - Easy Animator

To get started clone this repo to your local machine. This is the [link](https://course.ccs.neu.edu/cs5004/assignment7.html) to the assignment description.

## Workflow

Before creating a new branch, make sure to run `git pull` from `master` so you are up to date with the latest changes.

To create a new branch, run:
```
git checkout -b my-branch-name
```
To push changes to your branch, follow these steps:
1. Add all changes.
```
git add -A
```
2. Commit changes to the branch. **NOTE**: your commit message should be descriptive of the work you have done on the branch.
```
git commit -m "what I did on the branch"
```
3. Push your changes to the branch.
```
git push origin my-branch-name
```

If you are ready to merge your branch into `master`, go [here](https://github.com/alecherryy/java-animations/pulls) and create a new pull request. Your PR should have base set to `master` and compare set to `my-branch-name`. In the right column under **Reviewers**, select the other contributor to the repo and then hit `Create pull request`. After each branch is reviewed, it will be merged into `master`.

## Contributing
This is a school project, so no contributions outside of the team members can be accepted.