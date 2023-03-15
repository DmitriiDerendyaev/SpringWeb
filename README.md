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
- В классе MusicPlayer добавлен сеттер и конструктор по умолчанию
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

## lesson 7
- область видимости бинов SCOPE
- используется паттерн Singleton - тогда разные объекты ссылаются на один
- при использовании `scope="prototype"` создаются разные объекты, тогда:
```
MusicPlayer firstMusicPlayer = context.getBean("musicPlayer", MusicPlayer.class);
        MusicPlayer secondMusicPlayer = context.getBean("musicPlayer", MusicPlayer.class);

        boolean comparation = firstMusicPlayer == secondMusicPlayer;
        System.out.println(comparation);
        System.out.println(firstMusicPlayer);
        System.out.println(secondMusicPlayer);

        firstMusicPlayer.setVolume(5500);

        System.out.println(firstMusicPlayer.getVolume());
        System.out.println(secondMusicPlayer.getVolume());
```
Получится вывод:
```agsl
false
org.example.MusicPlayer@3ce1e309
org.example.MusicPlayer@6aba2b86
5500
100
```

## lesson 8
- жизненный цикл метода выглядит так:
![img.png](mdResourses/3.png)
- init-method
  - инициализаци ресурсов, подключение к БД, обращение к внешним файлм
- destroy-method
  - очищение ресурсов, зачистка и закрытие потоков ввода-вывода

Описание работы с методами:
- Мы можем сами создать методы инициализации и разрушения. Методы должны быть void, без параметров...
Инициализируем методы:
```agsl
    public void doMyInit(){
        System.out.println("Do my initialization");
    }

    public void doMyDestroy(){
        System.out.println("Bean was destroyed");
    }
```
В applicationContext.xml мы указываем на названия методов:
```agsl
<bean id="musicBean"
          class="org.example.ClassicalMusic"
          init-method="doMyInit"
          destroy-method="doMyDestroy"
    >
    </bean>
```

Так же можно использовать паттерн **Factory-method**, который запрещает использование new для создания новых объектов
- Создаем приватный конструктор `private ClassicalMusic(){}` и публичный статичный метод инициализции объекта:
```agsl
public static ClassicalMusic getClassicalMusic(){
        return new ClassicalMusic();
    }
```
И указать в applicationContext.xml `factory-method="getClassicalMusic"`

- ДЗ:
  - Spring не берет на себя управление жизненным циклом бинов при scope=prototype
  - Изменить Scope с singleton на prototype и изучить вывод
  - Получить несколько бинов.

Если мы используем несколько бинов и применяем к ним scope prototype, то init-method вызывается несколько раз, а destroy-method не вызывается вообще:
```java
        ClassicalMusic classicalMusic = context.getBean("musicBean", ClassicalMusic.class);
        ClassicalMusic classicalMusic1 = context.getBean("musicBean", ClassicalMusic.class);
        System.out.println(classicalMusic.getSong());
        System.out.println(classicalMusic1.getSong());

        System.out.println("\n\n");

        PopMusic popMusic = context.getBean("popBean", PopMusic.class);
        PopMusic popMusic1 = context.getBean("popBean", PopMusic.class);
        System.out.println(popMusic.getSong());
        System.out.println(popMusic1.getSong());
```
Тогда вывод будет:
```java
Initialized Classical music
Hungarian Rhapsody
Hungarian Rhapsody
        
Initialized Pop music
Initialized Pop music
Take on Me
Take on Me
Classical music was destroyed
```

## lesson 9
- работа с аннотациями вместо XML
- аннотация `@Component` над самим классом
- необходимо включить сканирование классов `<context:component-scan base-package="org.example"/>` - это все, что теперь есть в XML
- `@Component("musicBean")`, тогда id бина "musicBean", если без параметра аннотации, то имя класса с маленькой буквы
Получение бинов:
```java
        Music music = context.getBean("musicBean", Music.class);
        MusicPlayer musicPlayer = new MusicPlayer(music);

        musicPlayer.playMusic();

        Music music2 = context.getBean("popMusic", Music.class);
        MusicPlayer musicPlayer2 = new MusicPlayer(music2);

        musicPlayer2.playMusic();
```

## lesson 10 
- Spring аннотации, dependency injection
- `@Autowired` позволяет создавать зависимости DI 
```java
@Autowired
    public MusicPlayer(ClassicalMusic classicalMusic){
        this.classicalMusic = classicalMusic;
    }
```
И тогда при создании musicPlayer можно обходиться без инициализации, т.к. мы обращаемс по id к MusicPlayer, где уже проинициализирована с помощью `@Autowired` ClassicalMusic

Также в MusicPlayer можно вносить объекты классов, реализуемые от интерфейса Music

В случае, если MusicPlayer имеет реализацию для всех объектов по интерфейсу Music, а `@Component` будет у нескольких классов при Music, то может возникнуть неоднозначность и будет выброшена ошибка.
Например: 
```
No qualifying bean of type 'org.example.Music' available: expected single matching bean but found 3: classicalMusic,popMusic,rockMusic
```

- Убираем лишние `@Component` и при изменении способа внедрения зависимости через SETTER или через CONSTRUCTOR именно аннотация `@Autowired` указывает на то, через какой метод будет внедряться зависимость

- Можно внедрять зависимости даже в ПРИВАТНЫЕ ПОЛЯ:
```java
    @Autowired
    private Music music; //применен интерфейс музыка
```

### Внедрение нескольких зависимостей
- Просто создаем в MusicPlayer объекты классов, у которых включен `@Component` и прописываем КОНСТРУКТОР
```java
private ClassicalMusic classicalMusic;
    private RockMusic rockMusic;

    @Autowired
    public MusicPlayer(ClassicalMusic classicalMusic, RockMusic rockMusic) {
        this.classicalMusic = classicalMusic;
        this.rockMusic = rockMusic;
    }
```

ВАЖНО: выбирать можно любой способ внедрения зависимостей: setter, constructor, field - они все имеют одинаковую эффективность и реализацию

## lesson 11
- решение проблемы внедрение нескольких зависимостей 

Есть несколько вариантов конфигурации:
- Внедрение зависимости `@Autowired` и уточнение `@Qualifier` через поля:
  ```java
  @Autowired
  @Qualifier("classicalMusic")
  private Music music;
  ```
- Внедрение зависимости `@Autowired` и уточнение `@Qualifier` через конструктор:
  ```java
  private  Music music1;
      private  Music music2;
  
      public MusicPlayer(@Qualifier("rockMusic") Music music1,@Qualifier("classicalMusic") Music music2) {
          this.music1 = music1;
          this.music2 = music2;
      }
  ```
  
ДЗ:
- Создать список песен для двух классов RockMusic, ClassicalMusic
```java
List<String> listClassical = new ArrayList<>(Arrays.asList("Swan Lake: Spanish Dance", "Palladio", "The four seasons"));
```
```java
 List<String> listRock = new ArrayList<>(Arrays.asList("Compromise", "No one writes to the colonel", "Smells like teen spirit"));
```

- В классе PlayerMusic внедрить бины 
```java
    private Music rockMusic;
    private Music classicalMusic;

    public MusicPlayer(@Qualifier("rockMusic") Music music1, @Qualifier("classicalMusic") Music music2) {
        this.rockMusic = music1;
        this.classicalMusic = music2;
    }
```
- Реализовать Enum - CLASSICAL, ROCK
```java
public enum MusicGenre {
    CLASSICAL, ROCK
}
```
- PlayerMusic принимает на вход `Enum`, и через `Random` выводит случайную песню из списка
```java
public void playMusic(MusicGenre genre) {
        Random random = new Random();

        int randomNumber = random.nextInt(3);

        if (genre == MusicGenre.CLASSICAL) {
            // случайная классическая песня
            System.out.println(classicalMusic.getSong().get(randomNumber));
        } else {
            // случайная рок песня
            System.out.println(rockMusic.getSong().get(randomNumber));
        }
    }
```
- В TestSpring получить бин musicPlayer и вывести на экран содержимое
