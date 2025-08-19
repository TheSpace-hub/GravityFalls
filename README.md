# Gravity Falls

<a href="https://github.com/TheSpace-hub/GravityFalls/releases"><img src="https://img.shields.io/github/v/release/TheSpace-hub/GravityFalls?style=flat-square" alt="Последний Релиз"></a>
<a href="https://github.com/TheSpace-hub/GravityFalls/actions"><img src="https://img.shields.io/github/actions/workflow/status/TheSpace-hub/GravityFalls/build.yml?style=flat-square&label=Build" alt="Статус Билда"></a>
<a href="https://github.com/TheSpace-hub/GravityFalls?tab=MIT-1-ov-file"><img src="https://img.shields.io/github/license/TheSpace-hub/GravityFalls?style=flat-square" alt="License"></a>

Плагин изменяет гравитацию в мирах Minecraft, как для игроков, так и для всех живых сущностей.

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
messages:
  no-perms-to-change: '&cТебе нужно право gravity.change!'
  no-perms-to-reload: '&cТебе нужно право gravity.reload!'
  no-perms-to-show: '&cТебе нужно право gravity.show!'
  gravity-changed: '&aГравитация в мире {world} изменена на {new_value}.'
  gravity-reloaded: '&aКонфиг перезагружен.'
  use-change: "&cИспользуй /gravity <сила от 0 до 10> [мир] для изменения гравитации."
  use-reload: "&cИспользуй /gravity-reload для перезагрузки."
  use-show: "&cИспользуй /gravity-show для отображения миров."
  config-error: '&cНедопустимое значение {value} для мира {world} в config.yml!'
worlds:
  example-world: 5
```
