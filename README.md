# FIND LAW

## Environments

* ### Java 17+

## Details

### 1. Настройки портала

Настройки находятся в файле `application.properties`.

#### Настройка базы данных:

По умолчанию в качестве базы данных используется H2, которая создает на запущенном компьютере файл по указанному пути.
Например, для пути **~/findlaw** в Windows финальный путь будет выглядеть как **C:\Users\USERNAME\findlaw.mv.db**

Параметры настроек базы данных начинаются с префикса `spring.datasource.*`

#### Миграции базы данных:

Включение/отключение миграций задается в настройках параметром `spring.flyway.baseline-on-migrate`.
Если миграции включены к базе данных автоматически будут применены скрипты из папки относительно текущего каталога
**src\main\resources\db\migration**. Что позволит создать необходимые таблицы, функции и т.д.

#### Адрес портала:

По умолчанию `server.address=localhost` и `server.port=8080`.
В результате можно зайти на указанный адрес из браузера `http://localhost:8080/`.

#### Специфические настройки

* `portal.laws.updater.update-on-start` - true или false. Нужно ли обновить данные о законах при запуске.
* `portal.laws.updater.parser.type` - тип парсера. На данный момент есть только **remote** - обновление из удаленного источника.
* `portal.laws.updater.parser.path` - путь по которому будет работать парсер. Для **remote** указать адрес получения данных.

### 2. Код

#### Конфигурация:

* `UpdaterConfiguration` - данный класс отвечает за конфигурацию парсера `LawParser` и сервиса обновлений законов `LawUpdater`.
Если во время обновления законов произойдет ошибка в лог будет указано сообщение, а информация не будет записана в БД.

#### Сервисы:

* `LawsService` - сервис для взаимодействия с законами.

#### Контроллеры:

* `LawsController` - контроллер для обработки запросов на получение законов.

### 3. Веб

Статика расположена по пути: `src\main\resources\templates` - html шаблоны. `src\main\resources\static` - css и js файлы.