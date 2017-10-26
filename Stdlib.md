* Stdlib
  * In
  * Out
  * StdIn
    you need to save the input to a local variable
    * method read individual token
      * ``StdIn.isEmpty()`` : control Input time
      * ``StdIn.readInt()``
      * ``StdIn.readFloat()``
      * ``StdIn.readDouble()``
      * ``StdIn.readBoolean()``
      * ``StdIn.readString()``
      * ``StdIn.readInt()``
      * ``StdIn.readShort()``
      * ``StdIn.readLong()``
      * ``StdIn.readByte()``
      ```java
      public static void main(String[] ags) {
        double sum =0;
        while (!StdIn.isEmpty()) {
          double value = StdIn.readDouble();
          sum += value;
          StdOut.println(value);
          StdOut.println(sum);
        }
      }
      ```
    * read charactor
      * ``StdIn.readChar`` ： read only a char (byte)
      * ``StdIn.hasNextChar`` : does your input have more than one char ? Control input time
        ```java
        public static void main(String[] args) {
          while(StdIn.hasNextChar()) {
            char c = StdIn.readChar();
            StdOut.println(c);
          }
          ```
    * readline
      * ``StdIn.readline()``
      * ``StdIn.hasNextLine()``
      ```java
      public static void main(String[] ags) {
        while (StdIn.hasNextLine()) {
          String x = StdIn.readLine();
          StdOut.println(x);
        }
      }
      ```
    * read into list
      * `StdIn.readAll()`
      * `StdIn.readAllInts()`
      * `StdIn.readAllLines()`
      * `StdIn.readAllDoubles()`
      * `StdIn.readAllBooleans()`
      * `StdIn.readAllStrings()`
  * StdOut
    * `StdOut.print()`
    * `StdOut.println()`: print with a new line
    * ``StdOut.printf("this is %6.2f %6d %6.3e, %6s %b",1.2,5,23.333,true)``: print with a format string
  * StdDraw

* java.io
  * File
