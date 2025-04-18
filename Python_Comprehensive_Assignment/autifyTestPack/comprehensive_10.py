import time

from selenium import webdriver
from selenium.webdriver.common.by import By

url = "https://autify.com/"
driver = webdriver.Chrome()


def test_launch_app():
    driver.maximize_window()
    driver.get(url)


def test_mobile_link_verification():
    mobile_link_xpath = "(//div//a[contains(@href,'autify-mobile')]//img)[2]"
    mobile_link = driver.find_element(By.XPATH, mobile_link_xpath)
    mobile_link.click()
    time.sleep(3)
    assert driver.current_url == "https://autify.com/products/autify-mobile", "Failed to navigate to Autify mobile link's page"
    driver.back()


def test_web_link_verification():
    web_link_xpath = "(//div//a//img[contains(@class,'logo')])[1]"
    web_link = driver.find_element(By.XPATH, web_link_xpath)
    web_link.click()
    time.sleep(3)
    assert driver.current_url == "https://autify.com/", "Failed to navigate to web link's page"
    time.sleep(3)
    driver.quit()
