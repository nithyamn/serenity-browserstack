# serenity-browserstack

[Serenity](http://www.thucydides.info/docs/serenity/) Integration with BrowserStack SDK.

![BrowserStack Logo](https://d98b8t1nnulk5.cloudfront.net/production/images/layout/logo-header.png?1469004780)

<img src="http://www.thucydides.info/docs/serenity/images/serenity-logo.png" height = "100">

## Run Sample build
### Setup
* Clone the repo
* Replace YOUR_USERNAME and YOUR_ACCESS_KEY with your BrowserStack access credentials in browserstack.yml.
* Install dependencies `mvn install`

### Running your tests
- To run a sample test, run `mvn verify -P sample-test`
- To run local tests, run `mvn verify -P sample-local-test`
- To run parallel tests, run `mvn verify -P sample-parallel-test`

 Understand how many parallel sessions you need by using our [Parallel Test Calculator](https://www.browserstack.com/automate/parallel-calculator?ref=github)

## Integrate your test suite
This repository uses the BrowserStack SDK to run tests on BrowserStack. Follow the steps below to install the SDK in your test suite and run tests on BrowserStack:

* Create sample browserstack.yml file with the browserstack related capabilities with your [BrowserStack Username and Access Key](https://www.browserstack.com/accounts/settings) and place it in your root folder.
* Add maven dependency of browserstack-java-sdk in your pom.xml file
```sh
<dependency>
    <groupId>com.browserstack</groupId>
    <artifactId>browserstack-java-sdk</artifactId>
    <version>LATEST</version>
    <scope>compile</scope>
</dependency>
```
* Check your Test Runner Class and modify it to use Junit5 cucumber annotations. 
For example if you are using 
```
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/features/sample.feature")
```
Replace the above code and use the Junit5 code as shown below.
```
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("parallelFeatures")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.browserstack.cucumber")
```
* Make sure you replace your `junit-jupiter-engine` with `cucumber-junit-platform-engine` and add `cucumber-java`, `junit-platform-suite`, `junit-jupiter`  dependency in your pom.xml.
```
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-java</artifactId>
            <version>7.8.1</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-junit-platform-engine</artifactId>
            <version>LATEST</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.platform</groupId>
            <artifactId>junit-platform-suite</artifactId>
            <version>LATEST</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>5.9.1</version>
            <scope>test</scope>
        </dependency>
```
* Modify your build plugin to run tests by adding argLine `-javaagent:${com.browserstack:browserstack-java-sdk:jar}` and `maven-dependency-plugin` for resolving the `browserstack-java-sdk` dependency path.
```
            <plugin>
               <artifactId>maven-dependency-plugin</artifactId>
                 <executions>
                   <execution>
                     <id>getClasspathFilenames</id>
                       <goals>
                         <goal>properties</goal>
                       </goals>
                   </execution>
                 </executions>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-failsafe-plugin</artifactId>
                <version>3.0.0-M5</version>
                <configuration>
                    <argLine>
                        -javaagent:${com.browserstack:browserstack-java-sdk:jar}
                    </argLine>
                </configuration>
            </plugin>
```
* For parallel testing, add the properties and configuration parameters inside your `maven-failsafe-plugin`
```
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-failsafe-plugin</artifactId>
                <version>3.0.0-M5</version>
                <configuration>
                    <properties>
                        <configurationParameters>cucumber.execution.parallel.enabled = true
                            cucumber.execution.parallel.config.strategy = fixed
                            cucumber.execution.parallel.config.fixed.parallelism = 5</configurationParameters>
                    </properties>
                    <argLine>
                        -javaagent:${com.browserstack:browserstack-java-sdk:jar}
                    </argLine>
                </configuration>
            </plugin>
```
* Install dependencies `mvn compile`
* Run your test using mvn verify -P `your-profile-name`

## Notes
* You can view your test results on the [BrowserStack Automate dashboard](https://www.browserstack.com/automate)
* You can export the environment variables for the Username and Access Key of your BrowserStack account
  
  ```
  export BROWSERSTACK_USERNAME=<browserstack-username> &&
  export BROWSERSTACK_ACCESS_KEY=<browserstack-access-key>
  ```
