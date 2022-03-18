<div align="center">
<h1> billpwchan/External Sorting Reference Documentation </h1>

[![Issues](https://img.shields.io/github/issues/billpwchan/external-sorting?style=for-the-badge)](https://github.com/billpwchan/external-sorting/issues)
[![License](https://img.shields.io/github/license/billpwchan/external-sorting?style=for-the-badge)](https://github.com/billpwchan/external-sorting/blob/master/LICENSE)
[![LastCommit](https://img.shields.io/github/last-commit/billpwchan/external-sorting?style=for-the-badge)](https://github.com/billpwchan/external-sorting/blob/master/LICENSE)
[![CommitActivity](https://img.shields.io/github/commit-activity/y/billpwchan/external-sorting?style=for-the-badge)](https://github.com/billpwchan/external-sorting/commits/master)
[![RepoSize](https://img.shields.io/github/repo-size/billpwchan/external-sorting?style=for-the-badge)](https://github.com/billpwchan/external-sorting)
[![Languages](https://img.shields.io/github/languages/top/billpwchan/external-sorting?style=for-the-badge)](https://github.com/billpwchan/external-sorting)

</div>

## Deployment

### Pre-Requisite: Environment Variables (.inv)

```dotenv
TOTAL_LINES=100000     # Total number of lines to sort
MEMORY_CAP=1000         # Memory cap in lines
```

**IMPORTANT NOTE:** This is to simulate that the memory can only contain `MEMORY_CAP` lines at a time.

### Pre-Requisite: External Sorting Assumptions

1. The input file is in the format of one integer per Line
2. Memory Capacity is greater than the number of partitions (i.e., TOTAL_LINES / MEMORY_CAP)
3. All numbers are within the range of `-2,147,483,648` to `2,147,483,647` (i.e.,Int range in Java)

## How to Run

Use the default settings for External Sorting (TOTAL_LINES=100000, MEMORY_CAP=1000)

    java -jar ExternalSortingJAR.jar

**IMPORTANT NOTE:** This will create a folder named 'data' in the current directory. Please ensure that given the
program sufficient access right to create / delete files in the scope.

If you want to run the program with different settings, please use the following command (TOTAL_LINES=99999,
MEMORY_CAP=1000)

    java -jar ExternalSortingJAR.jar 99999 1000 

## Future Plans

- [ ] [Support ASC / DESC Sorting Options]()

-----------

## Contributor

[Bill Chan -- Main Developer](https://github.com/billpwchan/)