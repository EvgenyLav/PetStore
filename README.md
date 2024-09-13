Для запуска позитивных тестов необходимо выполнить команду ./gradlew clean myTags -x test -DcustomTags="Positive"
Для запуска негативных тестов необходимо выполнить команду ./gradlew clean myTags -x test -DcustomTags="Negative"
