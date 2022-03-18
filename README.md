<div align="center">
<h1> billpwchan/External Sorting Reference Documentation

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
3. All numbers are within the range of -2,147,483,648 to 2,147,483,647 (i.e.,Int range in Java)

### Idea

Install using [conda](https://docs.conda.io/en/latest/):

```bash
conda env create -f environment.yml
```

To export current environment, use the following command

```bash
conda env export > environment.yml
```

To update current environment with the latest dependencies, use the following command

```bash
conda env update --name futu_trade --file environment.yml --prune
```

### 2. Install FutuOpenD

For **Windows/MacOS/CentOS/Ubuntu**:

https://www.futunn.com/download/OpenAPI

Please do make sure that you have at least a LV1 subscription level on your interested quotes. For details, please refer
to https://openapi.futunn.com/futu-api-doc/qa/quote.html

**MAKE SURE YOU LOGIN TO FUTU OPEND FIRST BEFORE STARTING FUTU_ALGO!**

### 4. Download Data (e.g. 1M Data for max. 2 Years)

For **Windows**:

    python main_backend.py --force_update

For **MacOS/Linux**:

    python3 main_backend.py --force_update

### 4. Enjoy :smile:

## Command-line Interface Usages

Update all `K_1M` and `K_DAY` interval historical K-line data

    python main_backend.py -u   /   python main_backend.py --update

**IMPORTANT NOTE:** This will not override existing historical data if the file exists. The default number of days are
80 (will be customizable in the future).

If you want to refresh all data, use the following command instead (WITH CAUTION!)

    python main_backend.py -fu  /   python main_backend.py --force_update

Execute High-Frequency Trading (HFT) with a Pre-defined Strategy

    python main_backend.py -s MACD_Cross    /   python main_backend.py --strategy MACD_Cross

Execute Stock Filtering with Pre-defined Filtering Strategies

    python main_backend.py -f Volume_Threshold Price_Threshold   /   python main_backend.py --filter Volume_Threshold Price_Threshold

## GUI Usages

Start the GUI with `main.py` (**NOT FINISHED YET**)

```commandline
    python main.py
```

## Future Plans

- [ ] [NEED A GREAT NAME FOR THIS ALGO TRADE!!](https://github.com/billpwchan/futu_algo/issues/23)
- [ ] [Custom Backtesting Time Interval]()
- [ ] [Dynamic Instantiation](https://github.com/billpwchan/futu_algo/issues/18)

-----------

## Contributor

[Bill Chan -- Main Developer](https://github.com/billpwchan/)

## Disclaimer

Futures, stocks and options trading involves substantial risk of loss and is not suitable for every investor. The
valuation of futures, stocks and options may fluctuate, and, as a result, clients may lose more than their original
investment. The impact of seasonal and geopolitical events is already factored into market prices. The highly leveraged
nature of futures trading means that small market movements will have a great impact on your trading account and this
can work against you, leading to large losses or can work for you, leading to large gains.

If the market moves against you, you may sustain a total loss greater than the amount you deposited into your account.
You are responsible for all the risks and financial resources you use and for the chosen trading system. You should not
engage in trading unless you fully understand the nature of the transactions you are entering into and the extent of
your exposure to loss. If you do not fully understand these risks you must seek independent advice from your financial
advisor.

All trading strategies are used at your own risk.

Any content in this repository should not be relied upon as advice or construed as providing recommendations of any
kind. It is your responsibility to confirm and decide which trades to make. Trade only with risk capital; that is, trade
with money that, if lost, will not adversely impact your lifestyle and your ability to meet your financial obligations.
Past results are no indication of future performance. In no event should the content of this correspondence be construed
as an express or implied promise or guarantee.

This repository and its author are not responsible for any losses incurred as a result of using any of our trading
strategies. Loss-limiting strategies such as stop loss orders may not be effective because market conditions or
technological issues may make it impossible to execute such orders. Likewise, strategies using combinations of options
and/or futures positions such as “spread” or “straddle” trades may be just as risky as simple long and short positions.
Information provided in this correspondence is intended solely for informational purposes and is obtained from sources
believed to be reliable. Information is in no way guaranteed. No guarantee of any kind is implied or possible where
projections of future conditions are attempted.
