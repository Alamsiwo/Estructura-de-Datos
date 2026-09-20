import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DamasInglesasjava {

    String[][] tablero = new String[8][8]; // x/X = negras, o/O = blancas

    
    public static String[][] copiarTablero(String[][] original) {
        String[][] copia = new String[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                copia[i][j] = original[i][j];
            }
        }
        return copia;
    }

    public static boolean puedeCapturar(String[][] t, int f, int c) {
        String p = t[f][c];
        if (p.equals(" ")) return false;
        
        int[][] dirs;
        if (p.equals("x")) dirs = new int[][]{{1, -1}, {1, 1}};
        else if (p.equals("o")) dirs = new int[][]{{-1, -1}, {-1, 1}};
        else dirs = new int[][]{{-1, -1}, {-1, 1}, {1, -1}, {1, 1}}; 

        for (int[] d : dirs) {
            int mf = f + d[0], mc = c + d[1];
            int df = f + d[0] * 2, dc = c + d[1] * 2;
            if (df >= 0 && df < 8 && dc >= 0 && dc < 8) {
                boolean esEnemigo = (p.equalsIgnoreCase("x") && t[mf][mc].equalsIgnoreCase("o")) || (p.equalsIgnoreCase("o") && t[mf][mc].equalsIgnoreCase("x"));
                if (esEnemigo && t[df][dc].equals(" ")) return true;
            }
        }
        return false;
    }

    public static boolean hayCapturaObligatoria(String[][] t, int turno) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                String p = t[i][j];
                if ((turno == 1 && (p.equals("o") || p.equals("O"))) ||
                    (turno == 2 && (p.equals("x") || p.equals("X")))) {
                    if (puedeCapturar(t, i, j)) return true;
                }
            }
        }
        return false;
    }

    public static void procesarCapturaMultiple(String[][] tablero, int fila, int columna, Scanner scanner) {
        while (puedeCapturar(tablero, fila, columna)) {
            clrscr();
            printTablero(tablero);
            String p = tablero[fila][columna];
            System.out.println("¡CAPTURA MÚLTIPLE OBLIGATORIA con la ficha [" + p + "] en (" + (fila + 1) + "," + (columna + 1) + ")!");
            
            System.out.println("Ingresa la dirección para continuar capturando:");
            if (p.equals("x") || p.equals("o")) {
                System.out.println("Izquierda[i], Derecha[d]:");
            } else {
                System.out.println("Izquierda Superior[is], Derecha Superior[ds], Izquierda Inferior[ii], Derecha Inferior[di]:");
            }
            String op = scanner.next();

            int df = 0, dc = 0;
            if (p.equalsIgnoreCase("x")) {
                if (op.equalsIgnoreCase("i")) { df = 1; dc = -1; }
                else if (op.equalsIgnoreCase("d")) { df = 1; dc = 1; }
            } else if (p.equalsIgnoreCase("o")) {
                if (op.equalsIgnoreCase("i")) { df = -1; dc = -1; }
                else if (op.equalsIgnoreCase("d")) { df = -1; dc = 1; }
            } else {
                if (op.equalsIgnoreCase("is")) { df = -1; dc = -1; }
                else if (op.equalsIgnoreCase("ds")) { df = -1; dc = 1; }
                else if (op.equalsIgnoreCase("ii")) { df = 1; dc = -1; }
                else if (op.equalsIgnoreCase("di")) { df = 1; dc = 1; }
            }

            if (df != 0) {
                int mf = fila + df, mc = columna + dc;
                int nf = fila + df * 2, nc = columna + dc * 2;

                if (nf >= 0 && nf < 8 && nc >= 0 && nc < 8) {
                    boolean esEnemigo = (p.equalsIgnoreCase("x") && tablero[mf][mc].equalsIgnoreCase("o")) ||
                                       (p.equalsIgnoreCase("o") && tablero[mf][mc].equalsIgnoreCase("x"));
                    if (esEnemigo && tablero[nf][nc].equals(" ")) {
                        tablero[nf][nc] = tablero[fila][columna];
                        tablero[fila][columna] = " ";
                        tablero[mf][mc] = " ";
                        fila = nf;
                        columna = nc;
                        coronado(tablero);
                        continue;
                    }
                }
            }
            System.out.println("Dirección inválida o salto no permitido.");
        }
    }

    public static void clrscr(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void coronado(String[][] tablero) {
        for (int col = 0; col < 8; col++) {
            if (tablero[7][col].equals("x")) {
                tablero[7][col] = "X";
            }
            if (tablero[0][col].equals("o")) {
                tablero[0][col] = "O";
            }
        }
    }

    public static int contarFichas(String[][] tablero, String tipo1, String tipo2) {
        int total = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (tablero[i][j].equals(tipo1) || tablero[i][j].equals(tipo2)) {
                    total++;
                }
            }
        }
        return total;
    }

    public static void resetTablero(String[][] tablero){
        //Negras
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 8; j++){
                if((i + j) % 2 == 1){
                    tablero[i][j] = "x";
                }
            }
        }

        //Blancas
        for(int i = 5; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if((i + j) % 2 == 1){
                    tablero[i][j] = "o";
                }
            }
        }
        
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(tablero[i][j] == null){
                    tablero[i][j] = " ";
                }
            }
        }
    }    
    public static final String colRojo = "\u001B[31m";
    public static final String colAmarillo = "\u001B[33m";
    public static final String colNegro = "\u001B[30m";
    public static final String colRESET = "\u001B[0m";
    public static final String colVerde = "\u001B[32m";

    public static void printTablero(String[][] tablero) {
        
        System.out.println("< >  <1>  <2>  <3>  <4>  <5>  <6>  <7>  <8>");
        for (int i = 0; i < tablero.length; i++) {
            System.out.print("<" + (i + 1) + "> ");
            for(int j = 0; j < tablero[i].length; j++){
                if((i + j) % 2 == 1){
                    if(tablero[i][j].equals("x") || tablero[i][j].equals("X")){
                        System.out.print(colVerde + " [" + colRojo + tablero[i][j] + colVerde + "] " + colRESET);
                    }
                    else if(tablero[i][j].equals("o") || tablero[i][j].equals("O")){
                        System.out.print(colVerde + " [" + colAmarillo + tablero[i][j] + colVerde + "] " + colRESET);
                    }
                    else{
                        System.out.print(colVerde + " [" + colNegro + tablero[i][j] + colVerde + "] " + colRESET);
                    }
                }
                else{
                    if(tablero[i][j].equals("x") || tablero[i][j].equals("X")){
                        System.out.print(colNegro + " [" + colRojo + tablero[i][j] + colNegro + "] " + colRESET);
                    }
                    else if(tablero[i][j].equals("o") || tablero[i][j].equals("O")){
                        System.out.print(colNegro + " [" + colAmarillo + tablero[i][j] + colNegro + "] " + colRESET);
                    }
                    else{
                        System.out.print(colNegro + " [" + colNegro + tablero[i][j] + colNegro + "] " + colRESET);
                    }
                }
                
            }
            System.out.println();
        }
    }

    public static int movFicha(String[][] tablero, int turno, boolean finn, String tempString, int o, int x, Scanner scanner){
        do{
            finn = false;
            
            boolean obligatoria = hayCapturaObligatoria(tablero, turno);
            if (obligatoria) {
                System.out.println("¡CAPTURA OBLIGATORIA! Debes elegir una ficha que pueda capturar.");
            }

            System.out.println("Ingresa la coordenada de la ficha que deseas mover (fila, columna):");

            if (!scanner.hasNextInt()) {
                System.out.println("Entrada inválida. Debes ingresar números entre 1 y 8.");
                scanner.next(); 
                continue;
            }
            int fila = scanner.nextInt() - 1;

            if (!scanner.hasNextInt()) {
                System.out.println("Entrada inválida. Debes ingresar números entre 1 y 8.");
                scanner.next(); 
                continue;
            }
            int columna = scanner.nextInt() - 1;

            if (fila < 0 || fila >= 8 || columna < 0 || columna >= 8) {
                System.out.println("Coordenadas fuera de rango. Selecciona valores entre 1 y 8.");
                continue;
            }

            if (obligatoria && !puedeCapturar(tablero, fila, columna)) {
                System.out.println("Esta ficha no tiene capturas disponibles. Elige otra.");
                continue;
            }

            int nFila = fila, nCol = columna; 
            
            switch (tablero[fila][columna]){
                case " ":
                    System.out.println("No hay ficha en esa posición.");
                    break;
                case "x":
                    if(turno == 1){
                        System.out.println("No puedes mover fichas x/X en tu turno.");
                    }
                    else{
                        System.out.println("Ficha x seleccionada, ingresa la direccion a la que deseas moverla (Izquierda[i], Derecha[d]):");
                        String opcion = scanner.next();

                        if(opcion.equalsIgnoreCase("i")){
                            if(((fila+1)) > 7 || ((columna)-1) > 7 || ((fila)+1) < 0 || ((columna)-1) < 0){
                                System.out.println("No puedes mover a esa posición.");
                            }
                            else if(tablero[(fila)+1][(columna)-1].equals(" ")){
                                tempString = tablero[fila][columna];
                                tablero[fila][columna] = " ";
                                tablero[(fila)+1][(columna)-1] = tempString;
                                finn = true;
                            }
                            else if(tablero[(fila)+1][(columna)-1].equals("o") || tablero[(fila)+1][(columna)-1].equals("O")){
                                if(((fila)+2) > 7 || ((columna)-2) > 7 || ((fila)+2) < 0 || ((columna)-2) < 0 || !tablero[(fila)+2][(columna)-2].equals(" ")){
                                    System.out.println("No puedes mover a esa posición.");
                                }
                                else if(tablero[(fila)+2][(columna)-2].equals(" ")){
                                    tempString = tablero[fila][columna];
                                    tablero[fila][columna] = " ";
                                    tablero[(fila)+2][(columna)-2] = tempString;

                                    tablero[(fila)+1][(columna)-1] = " ";
                                    o = o - 1 ;
                                    finn = true;
                                    nFila = fila + 2; nCol = columna - 2;
                                }
                                else{
                                    System.out.println("No puedes mover a esa posición.");
                                }
                            }
                            else{
                                System.out.println("No puedes mover a esa posición.");
                            }
                        }
                        else if(opcion.equalsIgnoreCase("d")){
                            if(((fila)+1) > 7 || ((columna)+1) > 7 || ((fila)+1) < 0 || ((columna)+1) < 0){
                                System.out.println("No puedes mover a esa posición.");
                            }
                            else if(tablero[(fila)+1][(columna)+1].equals(" ")){
                                tempString = tablero[fila][columna];
                                tablero[fila][columna] = " ";
                                tablero[(fila)+1][(columna)+1] = tempString;
                                finn = true;
                            }
                            else if(tablero[(fila)+1][(columna)+1].equals("o") || tablero[(fila)+1][(columna)+1].equals("O")){
                                if(((fila)+2) > 7 || ((columna)+2) > 7 || ((fila)+2) < 0 || ((columna)+2) < 0 || !tablero[(fila)+2][(columna)+2].equals(" ")){
                                    System.out.println("No puedes mover a esa posición.");
                                }
                                else if(tablero[(fila)+2][(columna)+2].equals(" ")){
                                    tempString = tablero[fila][columna];
                                    tablero[fila][columna] = " ";
                                    tablero[(fila)+2][(columna)+2] = tempString;

                                    tablero[(fila)+1][(columna)+1] = " ";
                                    finn = true;
                                    o = o - 1 ;
                                    nFila = fila + 2; nCol = columna + 2;
                                }
                                else{
                                    System.out.println("No puedes mover a esa posición.");
                                }
                            }
                            else{
                                System.out.println("No puedes mover a esa posición.");
                            }
                        }
                        else{
                            System.out.println("Opción inválida.");
                        }
                    }
                    break;

                case "X":
                    if(turno == 1){
                        System.out.println("No puedes mover fichas x/X en tu turno.");
                    }
                    else{
                        System.out.println("Ficha X seleccionada, ingresa la direccion a la que deseas moverla");
                        System.out.println("(Izquierda Superior[is], Derecha Superior[ds], Izquierda Inferior[ii], Derecha Inferior[di]):");
                        String opcion = scanner.next();

                        if(opcion.equalsIgnoreCase("is")){
                            if(((fila)-1) > 7 || ((columna)-1) > 7 || ((fila)-1) < 0 || ((columna)-1) < 0){
                                System.out.println("No puedes mover a esa posición.");
                            }
                            else if(tablero[(fila)-1][(columna)-1].equals(" ")){
                                tempString = tablero[fila][columna];
                                tablero[fila][columna] = " ";
                                tablero[(fila)-1][(columna)-1] = tempString;
                                finn = true;
                            }
                            else if(tablero[(fila)-1][(columna)-1].equals("o") || tablero[(fila)-1][(columna)-1].equals("O")){
                                if(((fila)-2) > 7 || ((columna)-2) > 7 || ((fila)-2) < 0 || ((columna)-2) < 0 || !tablero[(fila)-2][(columna)-2].equals(" ")){
                                    System.out.println("No puedes mover a esa posición.");
                                }
                                else if(tablero[(fila)-2][(columna)-2].equals(" ")){
                                    tempString = tablero[fila][columna];
                                    tablero[fila][columna] = " ";
                                    tablero[(fila)-2][(columna)-2] = tempString;
                                    tablero[(fila)-1][(columna)-1] = " ";
                                    o = o - 1;
                                    finn = true;
                                    nFila = fila - 2; nCol = columna - 2;
                                }
                                else{
                                    System.out.println("No puedes mover a esa posición.");
                                }
                            }
                            else{
                                System.out.println("No puedes mover a esa posición.");
                            }
                        }
                        else if(opcion.equalsIgnoreCase("ds")){
                            if(((fila)-1) > 7 || ((columna)+1) > 7 || ((fila)-1) < 0 || ((columna)+1) < 0){
                                System.out.println("No puedes mover a esa posición.");
                            }
                            else if(tablero[(fila)-1][(columna)+1].equals(" ")){
                                tempString = tablero[fila][columna];
                                tablero[fila][columna] = " ";
                                tablero[(fila)-1][(columna)+1] = tempString;
                                finn = true;
                            }
                            else if(tablero[(fila)-1][(columna)+1].equals("o") || tablero[(fila)-1][(columna)+1].equals("O")){
                                if(((fila)-2) > 7 || ((columna)+2) > 7 || ((fila)-2) < 0 || ((columna)+2) < 0 || !tablero[(fila)-2][(columna)+2].equals(" ")){
                                    System.out.println("No puedes mover a esa posición.");
                                }
                                else if(tablero[(fila)-2][(columna)+2].equals(" ")){
                                    tempString = tablero[fila][columna];
                                    tablero[fila][columna] = " ";
                                    tablero[(fila)-2][(columna)+2] = tempString;
                                    tablero[(fila)-1][(columna)+1] = " ";
                                    o = o - 1;
                                    finn = true;
                                    nFila = fila - 2; nCol = columna + 2;
                                }
                                else{
                                    System.out.println("No puedes mover a esa posición.");
                                }
                            }
                            else{
                                System.out.println("No puedes mover a esa posición.");
                            }
                        }
                        else if(opcion.equalsIgnoreCase("ii")){
                            if(((fila)+1) > 7 || ((columna)-1) > 7 || ((fila)+1) < 0 || ((columna)-1) < 0){
                                System.out.println("No puedes mover a esa posición.");
                            }
                            else if(tablero[(fila)+1][(columna)-1].equals(" ")){
                                tempString = tablero[fila][columna];
                                tablero[fila][columna] = " ";
                                tablero[(fila)+1][(columna)-1] = tempString;
                                finn = true;
                            }
                            else if(tablero[(fila)+1][(columna)-1].equals("o") || tablero[(fila)+1][(columna)-1].equals("O")){
                                if(((fila)+2) > 7 || ((columna)-2) > 7 || ((fila)+2) < 0 || ((columna)-2) < 0 || !tablero[(fila)+2][(columna)-2].equals(" ")){
                                    System.out.println("No puedes mover a esa posición.");
                                }
                                else if(tablero[(fila)+2][(columna)-2].equals(" ")){
                                    tempString = tablero[fila][columna];
                                    tablero[fila][columna] = " ";
                                    tablero[(fila)+2][(columna)-2] = tempString;
                                    tablero[(fila)+1][(columna)-1] = " ";
                                    o = o - 1;
                                    finn = true;
                                    nFila = fila + 2; nCol = columna - 2;
                                }
                                else{
                                    System.out.println("No puedes mover a esa posición.");
                                }
                            }
                            else{
                                System.out.println("No puedes mover a esa posición.");
                            }
                        }
                        else if(opcion.equalsIgnoreCase("di")){
                            if(((fila)+1) > 7 || ((columna)+1) > 7 || ((fila)+1) < 0 || ((columna)+1) < 0){
                                System.out.println("No puedes mover a esa posición.");
                            }
                            else if(tablero[(fila)+1][(columna)+1].equals(" ")){
                                tempString = tablero[fila][columna];
                                tablero[fila][columna] = " ";
                                tablero[(fila)+1][(columna)+1] = tempString;
                                finn = true;
                            }
                            else if(tablero[(fila)+1][(columna)+1].equals("o") || tablero[(fila)+1][(columna)+1].equals("O")){
                                if(((fila)+2) > 7 || ((columna)+2) > 7 || ((fila)+2) < 0 || ((columna)+2) < 0 || !tablero[(fila)+2][(columna)+2].equals(" ")){
                                    System.out.println("No puedes mover a esa posición.");
                                }
                                else if(tablero[(fila)+2][(columna)+2].equals(" ")){
                                    tempString = tablero[fila][columna];
                                    tablero[fila][columna] = " ";
                                    tablero[(fila)+2][(columna)+2] = tempString;
                                    tablero[(fila)+1][(columna)+1] = " ";
                                    o = o - 1;
                                    finn = true;
                                    nFila = fila + 2; nCol = columna + 2;
                                }
                                else{
                                    System.out.println("No puedes mover a esa posición.");
                                }
                            }
                            else{
                                System.out.println("No puedes mover a esa posición.");
                            }
                        }
                        else{
                            System.out.println("Opción inválida.");
                        }
                    }
                    break;

                case "o":
                    if(turno == 2){
                        System.out.println("No puedes mover fichas o/O en tu turno.");
                    }
                    else{
                        System.out.println("Ficha o seleccionada, ingresa la direccion a la que deseas moverla (Izquierda[i], Derecha[d]):");
                        String opcion = scanner.next();

                        if(opcion.equalsIgnoreCase("i")){
                            if(((fila)-1) > 7 || ((columna)-1) > 7 || ((fila)-1) < 0 || ((columna)-1) < 0){
                                System.out.println("No puedes mover a esa posición.");
                            }
                            else if(tablero[(fila)-1][(columna)-1].equals(" ")){
                                tempString = tablero[fila][columna];
                                tablero[fila][columna] = " ";
                                tablero[(fila)-1][(columna)-1] = tempString;
                                finn = true;
                            }
                            else if(tablero[(fila)-1][(columna)-1].equals("x") || tablero[(fila)-1][(columna)-1].equals("X")){
                                if(((fila)-2) > 7 || ((columna)-2) > 7 || ((fila)-2) < 0 || ((columna)-2) < 0 || !tablero[(fila)-2][(columna)-2].equals(" ")){
                                    System.out.println("No puedes mover a esa posición.");
                                }
                                else if(tablero[(fila)-2][(columna)-2].equals(" ")){
                                    tempString = tablero[fila][columna];
                                    tablero[fila][columna] = " ";
                                    tablero[(fila)-2][(columna)-2] = tempString;

                                    tablero[(fila)-1][(columna)-1] = " ";
                                    x = x - 1;
                                    finn = true;
                                    nFila = fila - 2; nCol = columna - 2;
                                }
                                else{
                                    System.out.println("No puedes mover a esa posición.");
                                }
                            }
                            else{
                                System.out.println("No puedes mover a esa posición.");
                            }
                        }
                        else if(opcion.equalsIgnoreCase("d")){
                            if(((fila)-1) > 7 || ((columna)+1) > 7 || ((fila)-1) < 0 || ((columna)+1) < 0){
                                System.out.println("No puedes mover a esa posición.");
                            }
                            else if(tablero[(fila)-1][(columna)+1].equals(" ")){
                                tempString = tablero[fila][columna];
                                tablero[fila][columna] = " ";
                                tablero[(fila)-1][(columna)+1] = tempString;
                                finn = true;
                            }
                            else if(tablero[fila-1][columna+1].equals("x") || (tablero[fila-1][columna+1]).equals("X")){
                                if(((fila)-2) > 7 || ((columna)+2) > 7 || ((fila)-2) < 0 || ((columna)+2) < 0 || !tablero[(fila)-2][(columna)+2].equals(" ")){
                                    System.out.println("No puedes mover a esa posición.");
                                }
                                else if(tablero[(fila)-2][(columna)+2].equals(" ")){
                                    tempString = tablero[fila][columna];
                                    tablero[fila][columna] = " ";
                                    tablero[(fila)-2][(columna)+2] = tempString;

                                    tablero[(fila)-1][(columna)+1] = " ";
                                    finn = true;
                                    x = x - 1;
                                    nFila = fila - 2; nCol = columna + 2;
                                }
                                else{
                                    System.out.println("No puedes mover a esa posición.");
                                }
                            }
                            else{
                                System.out.println("No puedes mover a esa posición.");
                            }
                        }
                        else{
                            System.out.println("Opción inválida.");
                        }
                    }
                    break;

                case "O":
                    if(turno == 2){
                        System.out.println("No puedes mover fichas o/O en tu turno.");
                    }
                    else{
                        System.out.println("Ficha O seleccionada, ingresa la direccion a la que deseas moverla");
                        System.out.println("(Izquierda Superior[is], Derecha Superior[ds], Izquierda Inferior[ii], Derecha Inferior[di]):");
                        String opcion = scanner.next();

                        if(opcion.equalsIgnoreCase("is")){
                            if(((fila)-1) > 7 || ((columna)-1) > 7 || ((fila)-1) < 0 || ((columna)-1) < 0){
                                System.out.println("No puedes mover a esa posición.");
                            }
                            else if(tablero[(fila)-1][(columna)-1].equals(" ")){
                                tempString = tablero[fila][columna];
                                tablero[fila][columna] = " ";
                                tablero[(fila)-1][(columna)-1] = tempString;
                                finn = true;
                            }
                            else if(tablero[(fila)-1][(columna)-1].equals("x") || tablero[(fila)-1][(columna)-1].equals("X")){
                                if(((fila)-2) > 7 || ((columna)-2) > 7 || ((fila)-2) < 0 || ((columna)-2) < 0 || !tablero[(fila)-2][(columna)-2].equals(" ")){
                                    System.out.println("No puedes mover a esa posición.");
                                }
                                else if(tablero[(fila)-2][(columna)-2].equals(" ")){
                                    tempString = tablero[fila][columna];
                                    tablero[fila][columna] = " ";
                                    tablero[(fila)-2][(columna)-2] = tempString;
                                    tablero[(fila)-1][(columna)-1] = " ";
                                    x = x - 1;
                                    finn = true;
                                    nFila = fila - 2; nCol = columna - 2;
                                }
                                else{
                                    System.out.println("No puedes mover a esa posición.");
                                }
                            }
                            else{
                                System.out.println("No puedes mover a esa posición.");
                            }
                        }
                        else if(opcion.equalsIgnoreCase("ds")){
                            if(((fila)-1) > 7 || ((columna)+1) > 7 || ((fila)-1) < 0 || ((columna)+1) < 0){
                                System.out.println("No puedes mover a esa posición.");
                            }
                            else if(tablero[(fila)-1][(columna)+1].equals(" ")){
                                tempString = tablero[fila][columna];
                                tablero[fila][columna] = " ";
                                tablero[(fila)-1][(columna)+1] = tempString;
                                finn = true;
                            }
                            else if(tablero[(fila)-1][(columna)+1].equals("x") || tablero[(fila)-1][(columna)+1].equals("X")){
                                if(((fila)-2) > 7 || ((columna)+2) > 7 || ((fila)-2) < 0 || ((columna)+2) < 0 || !tablero[(fila)-2][(columna)+2].equals(" ")){
                                    System.out.println("No puedes mover a esa posición.");
                                }
                                else if(tablero[(fila)-2][(columna)+2].equals(" ")){
                                    tempString = tablero[fila][columna];
                                    tablero[fila][columna] = " ";
                                    tablero[(fila)-2][(columna)+2] = tempString;
                                    tablero[(fila)-1][(columna)+1] = " ";
                                    x = x - 1;
                                    finn = true;
                                    nFila = fila - 2; nCol = columna + 2;
                                }
                                else{
                                    System.out.println("No puedes mover a esa posición.");
                                }
                            }
                            else{
                                System.out.println("No puedes mover a esa posición.");
                            }
                        }
                        else if(opcion.equalsIgnoreCase("ii")){
                            if(((fila)+1) > 7 || ((columna)-1) > 7 || ((fila)+1) < 0 || ((columna)-1) < 0){
                                System.out.println("No puedes mover a esa posición.");
                            }
                            else if(tablero[(fila)+1][(columna)-1].equals(" ")){
                                tempString = tablero[fila][columna];
                                tablero[fila][columna] = " ";
                                tablero[(fila)+1][(columna)-1] = tempString;
                                finn = true;
                            }
                            else if(tablero[(fila)+1][(columna)-1].equals("x") || tablero[(fila)+1][(columna)-1].equals("X")){
                                if(((fila)+2) > 7 || ((columna)-2) > 7 || ((fila)+2) < 0 || ((columna)-2) < 0 || !tablero[(fila)+2][(columna)-2].equals(" ")){
                                    System.out.println("No puedes mover a esa posición.");
                                }
                                else if(tablero[(fila)+2][(columna)-2].equals(" ")){
                                    tempString = tablero[fila][columna];
                                    tablero[fila][columna] = " ";
                                    tablero[(fila)+2][(columna)-2] = tempString;
                                    tablero[(fila)+1][(columna)-1] = " ";
                                    x = x - 1;
                                    finn = true;
                                    nFila = fila + 2; nCol = columna - 2;
                                }
                                else{
                                    System.out.println("No puedes mover a esa posición.");
                                }
                            }
                            else{
                                System.out.println("No puedes mover a esa posición.");
                            }
                        }
                        else if(opcion.equalsIgnoreCase("di")){
                            if(((fila)+1) > 7 || ((columna)+1) > 7 || ((fila)+1) < 0 || ((columna)+1) < 0){
                                System.out.println("No puedes mover a esa posición.");
                            }
                            else if(tablero[(fila)+1][(columna)+1].equals(" ")){
                                tempString = tablero[fila][columna];
                                tablero[fila][columna] = " ";
                                tablero[(fila)+1][(columna)+1] = tempString;
                                finn = true;
                            }
                            else if(tablero[(fila)+1][(columna)+1].equals("x") || tablero[(fila)+1][(columna)+1].equals("X")){
                                if(((fila)+2) > 7 || ((columna)+2) > 7 || ((fila)+2) < 0 || ((columna)+2) < 0 || !tablero[(fila)+2][(columna)+2].equals(" ")){
                                    System.out.println("No puedes mover a esa posición.");
                                }
                                else if(tablero[(fila)+2][(columna)+2].equals(" ")){
                                    tempString = tablero[fila][columna];
                                    tablero[fila][columna] = " ";
                                    tablero[(fila)+2][(columna)+2] = tempString;
                                    tablero[(fila)+1][(columna)+1] = " ";
                                    x = x - 1;
                                    finn = true;
                                    nFila = fila + 2; nCol = columna + 2;
                                }
                                else{
                                    System.out.println("No puedes mover a esa posición.");
                                }
                            }
                            else{
                                System.out.println("No puedes mover a esa posición.");
                            }
                        }
                        else{
                            System.out.println("Opción inválida.");
                        }
                    }
                    break;
            }

            if (finn && (nFila != fila || nCol != columna)) {
                coronado(tablero);
                procesarCapturaMultiple(tablero, nFila, nCol, scanner);
            }

        }while(finn == false);

        coronado(tablero);

        if (turno == 1) {
            return 2;
        } else {
            return 1;
        }
    }

    
    public static void reproducirPartida(List<String[][]> historial, Scanner scanner) {
        System.out.println("\n--- REPRODUCCIÓN DE LA PARTIDA ---");
        for (int i = 0; i < historial.size(); i++) {
            clrscr();
            if (i == 0) {
                System.out.println("ESTADO INICIAL DEL TABLERO");
            } else {
                System.out.println("JUGADA NÚMERO: " + i);
            }
            printTablero(historial.get(i));
            System.out.println("\nPresiona ENTER para ver el siguiente turno...");
            scanner.nextLine(); 
        }
        System.out.println("--- FIN DE LA REPRODUCCIÓN ---");
    }

    public static void main(String[] args) {
        String[][] tablero = new String[8][8];
        int x = 12, o = 12, turno = 1; 
        resetTablero(tablero);
        
        Scanner scanner = new Scanner(System.in);
        List<String[][]> historial = new ArrayList<>();
        
        // Guardar el estado inicial
        historial.add(copiarTablero(tablero));
        
        do {
            clrscr();
            if (turno == 1) {
                clrscr();
                printTablero(tablero);
                System.out.println("//Turno de las fichas o/O //");
                turno = movFicha(tablero, turno, false, "", o, x, scanner);
            } else {
                clrscr();
                printTablero(tablero);
                System.out.println("//Turno de las fichas x/X //");
                turno = movFicha(tablero, turno, false, "", o, x, scanner);
            }
        
            
            historial.add(copiarTablero(tablero));
            
            o = contarFichas(tablero, "o", "O");
            x = contarFichas(tablero, "x", "X");
        
        } while (x != 0 && o != 0);

        if(o == 0){
            System.out.println("Las x/X GANAN!!!");
        }
        else{
            System.out.println("Las o/O GANAN!!!");
        }

        System.out.println("\n¿Deseas reproducir la partida jugada? (s/n): ");
        scanner.nextLine(); // Limpiar salto de línea previo
        String respuesta = scanner.nextLine();
        
        if (respuesta.equalsIgnoreCase("s")) {
            reproducirPartida(historial, scanner);
        }
    }
}
