Education Management System Application
=============================

[![Actions Status](https://img.shields.io/badge/JAVA-1.8-brightgreen)](https://img.shields.io/badge/JAVA-1.8-brightgreen)
[![Actions Status](https://img.shields.io/badge/Apache%20Maven-3.6.0-blue)](https://img.shields.io/badge/Apache%20Maven-3.6.0-blue)
[![Actions Status](https://img.shields.io/badge/Docker-20.10.7-orange)](https://img.shields.io/badge/Docker-20.10.7-orange)
[![Actions Status](https://img.shields.io/badge/PostgreSQL-11.1-blue)](https://img.shields.io/badge/PostgreSQL-11.1-blue)


Education Management System Application это приложение образовательная платформа - frontend
часть - GUI приложение.

+ Для дополнительной информации по backend части приложение,
  посетите [Education-Management-System-Application](https://github.com/Habatoo/EducationManagementSystemApplication).

INSTALLATION
------------

Проект не требует установки. Необхдимый софт для старта приложения указан в разделе REQUIREMENTS.
Структура проекта:

      src/main/java/educationManagementSystemGUI/cabinets   формы рабочих кабинетов и методов пользователей
      src/main/java/educationManagementSystemGUI/forms      формы авторизации и регстрации новых пользователей
      src/main/java/educationManagementSystemGUI/utils      утилиты для формирования http запросов с jwt
      src/test                                              тесты проекта
      pom.xml                                               настройка зависимостей проекта
      README                                                данный файл


REQUIREMENTS
------------

+ Java - 1.8 или старше.
+ Postgresql - 11 или старше.
+ Docker Engine Community - 20 или старше.
+ Apache Maven - 3.6.0 или старше.

QUICK START
-----------

Запускаем backend часть 
[проекта Education-Management-System-Application](https://github.com/Habatoo/EducationManagementSystemApplication), раздел QUICK START.

В командной строке запускаем следующую команду:

        $ sudo docker run --rm --name app -e POSTGRES_PASSWORD=1234567890 -e POSTGRES_USER=appuser -e POSTGRES_DB=app -d -p 5432:5432 -v app:/var/lib/postgresl/data  postgres
               (Linux)
        docker run --rm --name app -e POSTGRES_PASSWORD=1234567890 -e POSTGRES_USER=appuser -e POSTGRES_DB=app -d -p 5432:5432 -v app:/var/lib/postgresl/data  postgres
                  (Windows)

Далее стартуем Main. Frontend запущен.

USER GUI
-----------

При старте приложения пользователь подпадает на стратовое окно
![Login](https://user-images.githubusercontent.com/34543104/142753514-84bc55a0-25a5-4f4c-bb26-5b78a87d5439.png)

При успешной авторизации пользователь информируется об этом всплывающим окном.
![Login_success](https://user-images.githubusercontent.com/34543104/142753553-e6b8f85f-cda7-40c1-8155-95fee2edae0a.png)

Для новых пользователей предусмотрена регистрация. Регистрация происходит через форму, которая
представлена ниже.

![Register](https://user-images.githubusercontent.com/34543104/142753536-a50f0f1b-3a91-4b69-8bf7-21734312ddc2.png)

При успешной регистрации пользователь уведомляется об этом всплывающим окном.

![Register_succesful](https://user-images.githubusercontent.com/34543104/142753764-c90461c2-b8c2-42f2-a6be-0015ef50b35e.png)

В приложении по умолчанию создается три пользователя с разными правами. 

Пользователь ADMIN. С правами:
+ на создание и удаления пользователей
+ создание и удаления групп.

Кабинет пользователя ADMIN с доступными методами представлен ниже.

![Admin_cabinet](https://user-images.githubusercontent.com/34543104/142753569-549f7ec5-189f-4ffa-9a3e-f4d9aa5bd3be.png)

Пользователь TEACHER. С правами:
+ на создание и удаления уроков в группах, 
+ правами на добавление учеников в группы и исключения из них
+ правами на проставление оценок ученикам
+ 
Кабинет пользователя TEACHER с доступными методами представлен ниже.

![Teacher_cabinet](https://user-images.githubusercontent.com/34543104/142753587-68abd4e8-e456-49a2-aba0-f400accc0794.png)

Пользователь USER. С правами:
+ добавлять себя в группы
+ исключать себя из групп

Кабинет пользователя USER с доступными методами представлен ниже.

![User_cabinet](https://user-images.githubusercontent.com/34543104/142753601-df6145ee-f79b-4715-b78a-557a607b08f8.png)

При нажатии на кнопку Logout происходит выход из приложения. Об успешном выходе
пользватель уведомляется всплывающим окном.

![Logout](https://user-images.githubusercontent.com/34543104/142753621-9a8e1b40-af62-49e1-a8d2-a9a222b2189a.png)


AUTHORS
------------

+ Дмитрий Самусь dmitriysamus
+ Лаптенков Станислав habatoo