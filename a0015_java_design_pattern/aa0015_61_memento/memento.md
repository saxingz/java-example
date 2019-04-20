### 类别
行为型

### 意图
不侵犯封装，捕获和外部化。
保存对象的内部状态，以便稍后可以将对象恢复到此状态。

![file](http://www.saxing.cn/upload/2019/3/memento20190420191441447.png)

### 适用范围
- 必须保存对象状态的快照，以便稍后可以将其恢复到该状态
- 获取状态的接口将暴露实现细节并破坏对象的封装

### 主要代码
```java
public interface StarMemento {

}
```

```java
/**
     * Constructor
     */
    public Star(StarType startType, int startAge, int startMass) {
        this.type = startType;
        this.ageYears = startAge;
        this.massTons = startMass;
    }

    /**
     * Makes time pass for the star
     */
    public void timePasses() {
        ageYears *= 2;
        massTons *= 8;
        switch (type) {
            case RED_GIANT:
                type = StarType.WHITE_DWARF;
                break;
            case SUN:
                type = StarType.RED_GIANT;
                break;
            case SUPERNOVA:
                type = StarType.DEAD;
                break;
            case WHITE_DWARF:
                type = StarType.SUPERNOVA;
                break;
            case DEAD:
                ageYears *= 2;
                massTons = 0;
                break;
            default:
                break;
        }
    }

    StarMemento getMemento() {

        StarMementoInternal state = new StarMementoInternal();
        state.setAgeYears(ageYears);
        state.setMassTons(massTons);
        state.setType(type);
        return state;

    }

    void setMemento(StarMemento memento) {

        StarMementoInternal state = (StarMementoInternal) memento;
        this.type = state.getType();
        this.ageYears = state.getAgeYears();
        this.massTons = state.getMassTons();

    }
```
```java
public static void main(String[] args) {
        
        Stack<StarMemento> states = new Stack<>();

        Star star = new Star(StarType.SUN, 10000000, 500000);
        LOGGER.info(star.toString());
        states.add(star.getMemento());
        star.timePasses();
        LOGGER.info(star.toString());
        states.add(star.getMemento());
        star.timePasses();
        LOGGER.info(star.toString());
        states.add(star.getMemento());
        star.timePasses();
        LOGGER.info(star.toString());
        states.add(star.getMemento());
        star.timePasses();
        LOGGER.info(star.toString());
        while (states.size() > 0) {
            star.setMemento(states.pop());
            LOGGER.info(star.toString());
        }

    }
```

### 引用
* [Design patterns implemented in Java ](https://github.com/iluwatar/java-design-patterns)
* [Class Date](https://docs.oracle.com/javase/8/docs/api/java/util/Date.html)

