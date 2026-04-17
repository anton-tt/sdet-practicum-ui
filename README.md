# SDET Practicum: UI Tests
Проект реализует автоматизацию тестирования функционала на странице https://automationteststore.com/ 
с использованием Java, Selenium WebDriver и JUnit 5.

## Технологии
- **Язык:** Java 11
- **Браузер:** Chrome
- **Фреймворк:** JUnit 5
- **Инструменты:** Selenium WebDriver, WebDriverManager
- **Сборщик:** Maven
- **Отчеты:** Allure

## Тест-кейсы:
 `/docs/test-cases.md`

## Запуск тестов
1. Собрать проект и выполнить тесты Maven:

```bash
mvn clean test
```
2. Сформировать отчет Allure
```bash
allure serve target/allure-results
```