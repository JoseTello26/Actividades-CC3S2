# Examen Parcial

## Pregunta 1

1. ![image-20221110085800274](../../../../AppData/Roaming/Typora/typora-user-images/image-20221110085800274.png)

   El código compila y da como resultado en la pantalla `"match"`, debido a que la función `check` de Panda verifica recibe como argumento el objeto `p1` y una expresión lambda de la interfaz `Predicate`, el cual va a verificar si la expresión booleana que involucra al objeto de tipo `Panda` es verdadera o no, mediante el método `test()`. Luego, dentro de `check()`, si la expresión es verdadera, devuelve `match`, como se espera, pues `p1.age=1` es menor que `5`.

2. En la clase `Climber`, el método `check()` recibe como argumentos a la interfaz `Climb` y un entero que va a simbolizar la altura. Se le pasa como argumentos la expresión lambda `(h,m)->h.append(m).isEmpty()` y como altura `5`. Sin embargo, el programa no compila pues la expresión lambda está completando la definición del método `isTooHigh`, el cual recibe como parámetros dos números enteros, los cuales no tienen método `append()`. 

   ![image-20221110091817332](../../../../AppData/Roaming/Typora/typora-user-images/image-20221110091817332.png)

   Sin embargo, haciendo la corrección en la expresión de la manera `(h,m)->h>m`, se obtiene como verdadero cada vez que la altura sea mayor que el límite. Así, el programa retorna `"ok"`, pues `h=5 < 10`

   ![image-20221110091837759](../../../../AppData/Roaming/Typora/typora-user-images/image-20221110091837759.png)

3. Se puede crear la expresión lambda de la línea `17`, que va a definir el método `magic` haciendo que reciba como argumento la variable `d (double)` y retorne `"Poof"`

   ![image-20221110092645292](../../../../AppData/Roaming/Typora/typora-user-images/image-20221110092645292.png)

   Ambos retornan el mismo `String`, a pesar que `s` no sea instancia de la clase `Secret1`

   ![image-20221110092802437](../../../../AppData/Roaming/Typora/typora-user-images/image-20221110092802437.png)

4. Se añade el método main para poner a prueba el método `removeIf` con la expresión lambda que se le inserta

   ![image-20221110094132839](../../../../AppData/Roaming/Typora/typora-user-images/image-20221110094132839.png)

   Resulta que si funciona, pues si se le añaden caracteres fuera del rango de 'a' hasta 'z' (como '@'), entonces no los elimina

   ![image-20221110094230774](../../../../AppData/Roaming/Typora/typora-user-images/image-20221110094230774.png)

5. El código tiene una variable que no se modifica, que es `length`.
   Se itera del 1 al 3. Si se tiene un número de iteración par, entonces se define al método `get()` de la interfaz `Supplier<Integer>` de manera que retorne el valor de `length` . Si se tiene un número de iteración impar, se crea una variable `j=i`. Y asi, se define al método `get` de la interfaz `Supplier<Integer>` para que retorne `j`. Así, se imprime `length` o `i` para cada iteración desde `i=0` hasta `i=2`.

6. 



## Pregunta 2

1. Al ejecutar el programa da la siguiente salida

   ![image-20221110095416189](../../../../AppData/Roaming/Typora/typora-user-images/image-20221110095416189.png)

   Pues primero se añadieron a Cesar (VIP) y Jessica (no VIP) a un vuelo de negocios, luego se intentó eliminar a ambos pasajeros del vuelo, pudiendo solo eliminar a Jessica por no ser VIP. Luego, se añadió a Jessica a un vuelo económico y finalmente se imprimieron las listas de ambos vuelos.

2. El código muestra una covertura de clases del 83%, lo cual se explica pues solo se han testeado las clases Flight y Passenger, de las cuales Flight solo cubrío 5 de sus 6 métodos, pues no se probó el método `getFlightType()`

   ![image-20221112175739927](../../../../AppData/Roaming/Typora/typora-user-images/image-20221112175739927.png)

3. Porque tener tres tipos de vuelos distintos para una sola clase hace necesario que tengamos que implementar el método `getFlightType()` con condicionales respecto al tipo de vuelo. En cambio, si se separa en 3 cases heredadas de una clase Flight, se tendrán definidos de antemano los tipos de vuelos, en lugar de tenerlos en tiempo de ejecución

4. Las clases añadidas `EconomicFLight` y `BusinessFlight` compilan de manera exitosa, al igual que el resto del paquete `Fase2`. Esto implica que la refactorización no ha afectado la ejecución del programa, sin embargo, aun falta por verificar la lógica del mismo mediante pruebas unitarias.

5. El test `AirportTest` compila y pasa todas las pruebas de manera exitosa, teniendo un cumplimiento de 100% de cobertura de código. Antes de las pruebas por cada tipo de vuelo, como condición inicial se inicializa el tipo de vuelo con un constructor, sea `EconomicFlight()`  o `BusinessFlight()`. La refactorización permitió que se distribuyeran mejor los métodos para probar,  haciendo una cobertura total del código.

6. La regla de tres indica que no se pueden tener tres o mas instancias de código muy similares, por lo que se requeriría refactorizar y llevar las similitudes a un solo procedimiento. Sirve para evitar la repetición de código. En este caso, se tiene que teniendo todo hecho 

   En el caso del código de prueba, añadir la tercera clase `PremiumFlight` hace que se repitan las mismas pruebas anteriores una tercera vez, por lo que conviene agrupar ciertas pruebas usando `@Nested`.

7.  Se tiene que se agrega un diseño inicial sin los requerimientos de un vuelo Premium, pues se agregan o eliminan cualquier tipo de pasajeros.

   ```java
   package org.example.ArchivosPregunta2.Fase4.Produccion;
   
   public class PremiumFlight extends Flight {
   
       public PremiumFlight(String id) {
           super(id);
       }
   
       @Override
       public boolean addPassenger(Passenger passenger) {
           return passengers.add(passenger);
       }
   
       @Override
       public boolean removePassenger(Passenger passenger) {
           return passengers.remove(passenger);
       }
   }
   ```

8. Se inserta el código requerido para realizar las pruebas para un usuario VIP y uno regular en un vuelo Premium:

   - Falla la condición de no agregar o eliminar pasajeros regulares
   - Cumple la condición de agregar o eliminar pasajeros VIP

   ```java
   @DisplayName("Dado que hay un vuelo Premium")
       @Nested
       class PremiumFlightTest {
           private Flight premiumFlight;
           private Passenger jessica;
           private Passenger cesar;
   
           @BeforeEach
           void setUp() {
               premiumFlight = new PremiumFlight("3");
               jessica = new Passenger("Jessica", false);
               cesar = new Passenger("Cesar", true);
           }
   
           @Nested
           @DisplayName("Cuando tenemos un pasajero regular")
           class RegularPassenger {
   
               @Test
               @DisplayName("Entonces no puede agregarlo o eliminarlo de un vuelo premium")
               public void testPremiumFlightRegularPassenger() {
                   assertAll("Verifica todas las condiciones para un pasajero regular y un vuelo premium",
                           () -> assertEquals(false, premiumFlight.addPassenger(jessica)),
                           () -> assertEquals(0, premiumFlight.getPassengersSet().size()),
                           () -> assertEquals(false, premiumFlight.removePassenger(jessica)),
                           () -> assertEquals(0, premiumFlight.getPassengersSet().size())
                   );
               }
           }
   
           @Nested
           @DisplayName("Cuando tenemos un pasajero VIP")
           class VipPassenger {
   
               @Test
               @DisplayName("Luego puedes agregarlo o eliminarlo de un vuelo premium")
               public void testPremiumFlightVipPassenger() {
                   assertAll("Verifica todas las condiciones para un pasajero VIP y un vuelo premium",
                           () -> assertEquals(true, premiumFlight.addPassenger(cesar)),
                           () -> assertEquals(1, premiumFlight.getPassengersSet().size()),
                           () -> assertEquals(true, premiumFlight.removePassenger(cesar)),
                           () -> assertEquals(0, premiumFlight.getPassengersSet().size())
                   );
               }
           }
   
           @DisplayName("Entonces no puedes agregarlo a un vuelo premium mas de una vez.")
           @RepeatedTest(5)
           public void testPremiumFlightVipPassengerAddedOnlyOnce(RepetitionInfo repetitionInfo) {
               for (int i = 0; i < repetitionInfo.getCurrentRepetition(); i++) {
                   premiumFlight.addPassenger(cesar);
               }
               assertAll("Verifica que un pasajero VIP se pueda agregar a un vuelo de negocios solo una vez",
                       () -> assertEquals(1, premiumFlight.getPassengersSet().size()),
                       () -> assertTrue(premiumFlight.getPassengersSet().contains(cesar)),
                       () -> assertTrue(new ArrayList<>(premiumFlight.getPassengersSet()).get(0).getName().equals("Cesar"))
               );
           }
       }
   ```

9. Ahora, se agrega la restricción de solo poder agregar pasajeros VIP al vuelo Premium, y se deja tal cual la opción de poder eliminarlos.

   ```java
   package org.example.ArchivosPregunta2.Fase4.Produccion;
   
   public class PremiumFlight extends Flight {
   
       public PremiumFlight(String id) {
           super(id);
       }
   
       @Override
       public boolean addPassenger(Passenger passenger) {
           if (passenger.isVip()) {
               return passengers.add(passenger);
           }
           return false;
       }
   
       @Override
       public boolean removePassenger(Passenger passenger) {
           return passengers.remove(passenger);
       }
   }
   ```

10. Se agregó al código de pruebas, el código de prueba de la fase 4, cambiando la lista de pasajeros por un conjunto de tipo `HashSet`. Luego, se añaden las siguientes líneas para verificar que, por mas que se llame al método 5 veces para agregar al pasajero `cesar`, se verifica que solo hay un pasajero `cesar` en el conjunto de pasajeros del vuelo Premium.

    ```java
    @DisplayName("Entonces no puedes agregarlo a un vuelo premium mas de una vez.")
    @RepeatedTest(5)
    public void testBusinessFlightVipPassengerAddedOnlyOnce(RepetitionInfo repetitionInfo) {
        for (int i = 0; i < repetitionInfo.getCurrentRepetition(); i++) {
            premiumFlight.addPassenger(cesar);
        }
        assertAll("Verifica que un pasajero VIP se pueda agregar a un vuelo de negocios solo una vez",
                () -> assertEquals(1, premiumFlight.getPassengersSet().size()),
                () -> assertTrue(premiumFlight.getPassengersSet().contains(cesar)),
                () -> assertTrue(new ArrayList<>(premiumFlight.getPassengersSet()).get(0).getName().equals("Cesar"))
        );
    }
    ```

## Pregunta 3

1. Los problemas son:

   - El nombre de la prueba no es claro.
   - Los nombres de las variables son redundantes y no aportan información al lector de la prueba.
   - El parámetro `value` del constructor `Invoice()` debe ser de tipo `double`, por lo que se debe hacer una llamada al método `BigDecimal.doubleValue()`.

2. ```java
   class InvoiceTest {
       @DisplayName("Prueba del método calculate")
       @Test
       void calculateTest(){
           double value = new BigDecimal("2500").doubleValue();
           String country = "NL";
   
           Invoice customer = new Invoice(value, country, Invoice.CustomerType.COMPANY);
           double result = customer.calculate();
           assertThat(result).isEqualTo(250);
       }
   }
   ```

3. ```java
   public class InvoiceBuilder {
       private String country = "NL";
       private Invoice.CustomerType customerType = Invoice.CustomerType.PERSON;
       private double value = 500;
       public InvoiceBuilder withCountry(String country){
           this.country = country;
           return this;
       }
       public InvoiceBuilder asCompany(){
           this.customerType = Invoice.CustomerType.COMPANY;
           return this;
       }
       public InvoiceBuilder withAValueOf(double value){
           this.value = value;
           return this;
       }
       public Invoice build(){
           return new Invoice(value, country, customerType);
       }
   }
   ```

4. Se crea un objeto `Invoice` con:

   - `value = 2500`
   - `country = "NL"`
   - `customerType = COMPANY`

   ```java
   Invoice customer = new InvoiceBuilder().withAValueOf(value).asCompany().build();
   ```

   Con lo cual, reemplazando en las pruebas, se ve que pasan sin problemas:}

   ![image-20221112225153634](../../../../AppData/Roaming/Typora/typora-user-images/image-20221112225153634.png)

5. Al agregar los nuevos constructores, se observa que si agregamos las pruebas para verificar que los objetos creados cumplen con la definición que se le da, entonces las pruebas pasan.

   ```java
   Invoice customer = new InvoiceBuilder().withAValueOf(value).fromTheUS();
   
   double result = customer.calculate();
   assertThat(result).isEqualTo(250);
   assertThat(customer.getCustomerType()).isEqualTo(Invoice.CustomerType.PERSON);
   assertThat(customer.getCountry()).isEqualTo("US");
   ```

   ![image-20221112230009862](../../../../AppData/Roaming/Typora/typora-user-images/image-20221112230009862.png)

6. Al agregar el código nuevo a las pruebas, se observa que pasan sin problemas, confirmando que el metodo `calculate()` funciona correctamente.

   ![image-20221112225521267](../../../../AppData/Roaming/Typora/typora-user-images/image-20221112225521267.png)

   