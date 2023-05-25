from behave import given, when, then
from selenium.webdriver.common.by import By


@given('I am on the Sauce Demo login page')
def step_impl(context):
    context.driver.get('https://www.saucedemo.com/')


@when('I click the login button')
def step_impl(context):
    login_button = context.driver.find_element(By.ID, 'login-button')
    login_button.click()


@when('I click the menu button')
def step_impl(context):
    menu_button = context.driver.find_element(By.ID, 'react-burger-menu-btn')
    menu_button.click()


@when('I click the logout button')
def step_impl(context):
    logout_link = context.driver.find_element(By.ID, 'login-button')
    logout_link.click()


@then('I should be logged in successfully')
def step_impl(context):
    title = context.driver.find_element(By.CLASS_NAME, 'app_logo')
    assert title.is_displayed()


@then('I should see an error message')
def step_impl(context):
    error_message = context.driver.find_element(By.CSS_SELECTOR, '.error-message-container.error')
    assert error_message.is_displayed()


@then('I should be logged out successfully')
def step_impl(context):
    login_button = context.driver.find_element(By.ID, 'login-button')
    assert login_button.is_displayed()


@then('I should see the products page')
def step_impl(context):
    products_string = context.driver.find_element(By.CLASS_NAME, 'title')
    assert products_string.is_displayed()


@when("I enter an invalid username")
def step_impl(context):
    username = context.driver.find_element(By.ID, 'user-name')
    username.send_keys('invalid_user')


@when("I enter a valid password")
def step_impl(context):
    password = context.driver.find_element(By.ID, 'password')
    password.send_keys('secret_sauce')


@when("I enter a valid username")
def step_impl(context):
    username = context.driver.find_element(By.ID, 'user-name')
    username.send_keys('standard_user')


@when("I enter an empty username")
def step_impl(context):
    username = context.driver.find_element(By.ID, 'user-name')
    username.send_keys('')


@when("I enter an empty password")
def step_impl(context):
    password = context.driver.find_element(By.ID, 'password')
    password.send_keys('')


@when("I enter an invalid password")
def step_impl(context):
    password = context.driver.find_element(By.ID, 'password')
    password.send_keys('hello world')