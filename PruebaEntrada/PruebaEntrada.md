# Prueba de Entrada

1. **Salida del programa:**

   ```
   qpzj
   ```

   Se debe a que la función main() crea un nuevo objeto de la clase Fennec con el constructor que acepta un entero como parámetro, el cual a su vez llama al constructor Fox() que acepta un String como argumento, el cual llama al otro constructor Fox() con argumento long, el cual a su vez llama por defecto al constructor Canine() de la clase superior, en la cual añade a logger el caracter "q", para luego continuar con el constructor anterior y añadir "p", y luego con el anterior para añadir "z" y el inicial que añade "j"

2. **Respuesta:** e) El programa compila e imprime 3

3. - a) Una clase puede extender directamente cualquier número de clases. **Falso**
   - b) Una clase puede implementar cualquier número de interfaces. **Verdadero, pueden implementarse mas de una interfaz**
   - c) Todas las variables heredan java.lang.Object. **Verdadero, todas las clases son heredadas de la clase Object, y por eso tiene sus métodos y su constructor por defecto**
   - d) Si la clase A se extiende por B, entonces B es una superclase de A. **Falso, B es la clase heredada de A**
   - e) Si la clase C implementa la interfaz D, entonces C es un subtipo de D. **Verdadero**
   - f) La herencia múltiple es la propiedad de una clase de tener múltiples superclases directas. **Verdadero**

4. ```java
   public class Howler {
       public Howler(long shadow){
           super(); //llama a 
       }
       private Howler(int moon){
           super();
       }
   }
   
   class Wolf extends Howler{
       protected Wolf(String stars){
           super(2L);
       }
       public Wolf(){
           this("stars");
       }
   }
   ```

   

5. El programa no compila, y da el siguiente mensaje de error
   ![image-20221002221008237](../../../../AppData/Roaming/Typora/typora-user-images/image-20221002221008237.png)
   pues en la línea 7:

   ```java
   check((h,m)->h.append(m).isEmpty(), 5);
   ```

   Se hace llamada al método check() de la clase Climber. Sin embargo, el primer parámetro de check() es el resultado de aplicar la función isTooHigh asociado a la interface Climb y le aplica la definición:

   ```java
   (h,m)->h.append(m).isEmpty() //Define el método isTooHigh asociado a Climb
   ```

   Sin embargo, la función isTooHigh() solo acepta parámetros de tipo primitivo entero, los cuales no tienen método append() como el que se le intenta aplicar a "h" y ocurre error al ser int un tipo primitivo y no una clase.

6. - **a)** Si no tiene acceso a SuperFastList aun se pueden usar sus implementaciones porque son un suptipo de la interfaz SuperFastCollection

     **b)** Es cierto, si hay cambios en la interfaz, la clase implementada sufriría los mismos cambios al ser un subtipo de la primera.

     **c)** Es cierto, pero SuperFastList puede pasar como si fuera del tipo SuperFastCollection y aun se podrían usar sus métodos

   - **c)** Puede que no sea posible reemplazar SuperFastCollection por java.util.List, porque SuperAwesomeApp puede llamar a métodos que no pertenecen a java.util.List

   - **b)** SuperFastCollection / ExtraSuperFastList