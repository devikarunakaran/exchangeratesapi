# exchangeratesapi
Exchange Rates API

-- Loads rates from file (from different sources like bloomberg, reuters) and provides end points to access the rates 

/ExchRates/loadRatesFromFile
1. Loads exchange rates from uploaded file

/ExchRates/getLatestRates/from/{from}/to/{to}
2. Retrieve latest rates for  specific currency pair

/ExchRates/getSpecificRatesForCurrencyPair
3. Retrieve rates for currency pair on a specific date
