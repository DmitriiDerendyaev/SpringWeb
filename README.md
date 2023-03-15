## lesson 2
- Настройка конфигурационного файла
- Первый запуск приложения

## lesson 4
- Работа над Inversion of Control
- Работа с имплементацией и созданием как самих объектов, так и зависимостей через интерфейсы
  - ДЗ: создать еще один жанр музыки

## lesson 5
- Работа над **Dependency Injection**(Внедрение зависимостей) через КОНСТРУКТОР
- Теперь зависимости внедряются не вручную, а с помощью конфигурационного файла, бины все делают сами

Вместо:
```
Music music = context.getBean("musicBean", Music.class);
MusicPlayer musicPlayer = new MusicPlayer(music);
```
Делаем:
```agsl
<bean id="musicPlayer"
          class="org.example.MusicPlayer">
        <constructor-arg ref="musicBean"/>
    </bean>
```
1) Spring изначально создает Beans, в даннос случае `class="org.example.RockMusic"`
2) Затем Spring создает Bean `class="org.example.MusicPlayer">` и передает ему по ссылке `<constructor-arg ref="musicBean"/>`

![img.png](mdResourses/img.png)

## lesson 6
- Работа над **Dependency Injection**(Внедрение зависимостей) через SETTER
- В классе MusicPlayer добавлен сеттер и конструтор по умолчанию
- Соттвестванно изменены `<constructor-arg ref="musicBean"/>` на `<property name="music" ref="musicBean"/>`
- ВАЖНО: здесь все еще внедряется ссылочное значение 
- Передача параетров в setter, назначение атрибутов через DI
![img.png](mdResourses/2.png)
  - ДЗ:
    - Добавить Список проигрываемых композиций
    - Добавить Setter для устновки знчений
    - Изменить или переопределить метод, выводящий список воспроизводимой музык
    - Досоздавать классы альтернативной музыки
    - Внедрить зависимость, передав список
```agsl
<bean id="musicPlayer"
          class="org.example.MusicPlayer">
        <property name="musicList">
            <list>
                <ref bean="musicBean1"/>
                <ref bean="musicBean2"/>
                <ref bean="musicBean3"/>
            </list>
        </property>

        <property name="name" value="${musicPlayer.name}"/>
        <property name="volume" value="${musicPlayer.volume}"/>
    </bean>
```




