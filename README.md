# serenity-browserstack

[Serenity](http://www.thucydides.info/docs/serenity/) Integration with BrowserStack SDK.

![BrowserStack Logo](https://d98b8t1nnulk5.cloudfront.net/production/images/layout/logo-header.png?1469004780)

<img src="https://serenity-bdd.info/wp-content/uploads/elementor/thumbs/serenity-bdd-pac9onzlqv9ebi90cpg4zsqnp28x4trd1adftgkwbq.png" height = "100">

## Run Sample build
### Setup
* Clone the repo
* Replace YOUR_USERNAME and YOUR_ACCESS_KEY with your BrowserStack access credentials in browserstack.yml.
* Install dependencies `mvn install`
* You can setup environment variables for all sample repos (see Notes) or update `serenity.conf` file with your [BrowserStack Username and Access Key](https://www.browserstack.com/accounts/settings)

### Running your tests
- To run a sample test, run `mvn verify -P sample-test`
- To run local tests, run `mvn verify -P sample-local-test`

 Understand how many parallel sessions you need by using our [Parallel Test Calculator](https://www.browserstack.com/automate/parallel-calculator?ref=github)

## Notes
* You can view your test results on the [BrowserStack Automate dashboard](https://www.browserstack.com/automate)
* You can export the environment variables for the Username and Access Key of your BrowserStack account
  
  ```
  export BROWSERSTACK_USERNAME=<browserstack-username> &&
  export BROWSERSTACK_ACCESS_KEY=<browserstack-access-key>
  ```
