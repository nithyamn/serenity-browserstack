Feature: BrowserStack Demo1

    Scenario: Add product to cart1
        Given I am on the website 'https://www.bstackdemo.com'
        When I select a product and click on 'Add to cart' button
        Then the product should be added to cart
