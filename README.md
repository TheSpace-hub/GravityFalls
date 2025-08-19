# Gravity Falls

![License](https://img.shields.io/github/license/TheSpace-hub/GravityFalls?style=flat-square)

Плагин изменяет гравитацию в мирах Minecraft.

## Как использовать?

### Команды

| Команда          | Параметры    | Право          | Описание                                 |
|------------------|--------------|----------------|------------------------------------------|
| `gravity`        | <сила> [мир] | gravity.change | Изменяет гравитацию в мире.              |
| `gravity-reload` | -            | gravity.reload | Обновляет конфиг.                        |
| `gravity-show`   | -            | gravity.show   | Выводит информацию о гравитации в мирах. |

### Конфиг

Основной файл конфигурации `GravityFalls/config.yml`.

В полях _no-perms_, _gravity-changed_ и _use_ указываются сообщения для недостатка прав, изменения гравитации и помощи в
использовании соответственно.

В разделе _worlds_ перечисляются миры, в которых установлена гравитация. Если мира нет, то гравитация отсутствует.

```yml
no-perms: '&cТебе нужно право gravity.change!'
gravity-changed: '&aГравитация в мире {world} изменена на {new_value}.'
use: "&cИспользуй /gravity <сила от 0 до 10> [мир] для изменения гравитации."
worlds:
  example-world: 5
```
