# Задача

Спроектировать и реализовать набор классов\интерфейсов, 
которые будут представлять иммутабельную коллекцию - приоритетную очередь.

Обязательные критерии, которым должна удовлетворять работа:
- Предусмотреть две реализации: очередь ограниченного размера и очередь неограниченного размера.
- Предусмотреть возможность задания приоритета элемента при добавлении в очередь.
- Предусмотреть возможность извлечения элемента с наивысшим приоритетом.
- Предусмотреть возможность извлечения элемента с наивысшим приоритетом и удаления его из очереди.
- Добавить возможность итерирования по элементам очереди.
- Добавить способы преобразования очереди в список, массив.
- Написать тесты для проверки корректности работы реализованных классов\интерфейсов.

За безупречное выполнение вышеописанных условий вы получите максимальный балл в пять единиц. 

Будем плюсом, если вы усовершенствуете либо обогатите вашу реализацию как-либо полезным образом, например:
- Добавите в API безопасные методы для работы с очередью (как, например, `headOption` у класса `List`)
- Реализуете стандартные интерфейсы коллекций (например, `Iterable`)
- Сделаете реализацию на основе кучи (heap), либо примените другие оптимизации для улучшения производительности
- Добавите возможность задания стратегии разрешения коллизий приоритетов

Помимо дополнительно приобретённых навыков, подобные улучшения имплементации увеличат ваши шансы получить максимальный балл.

Для написания тестов рекомендуется обратить внимение на библиотеки `scalatest` и `munit`.
Примеры конфигурации проекта и работы с зависимостями в sbt можно найти здесь: https://www.scala-sbt.org/1.x/docs/Library-Dependencies.html