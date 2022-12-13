/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Brais
 */
public class Game {

    public final int VACIO = 0;
    public final int AMARILLO = 1;
    public final int ROJO = 2;
    private int[][] casillas;
    private int filas;
    private int columnas;
    private int turno;

    public Game(int filas, int columnas) {
        turno = ROJO;
        this.filas = filas;
        this.columnas = columnas;
        this.casillas = new int[this.filas][this.columnas];
        iniciarCasillas();

    }

    public Game() {
        turno = ROJO;
        filas = 6;
        columnas = 7;
        this.casillas = new int[this.filas][this.columnas];
        iniciarCasillas();
    }

    /**
        Pone todas las casillas a valor vacio
     */
    public void iniciarCasillas() {
        for (int i = filas - 1; i >= 0; i--) {
            for (int j = 0; j < columnas; j++) {
                casillas[i][j] = VACIO;
            }
        }
    }

    /**
        Cambia el valor de la ultima ficha de la columna pasada por el valor
        del turno actual, cambia el turno y devuelve -1 si no se puede añadir
        a esa columna más fichas o el numero de fila si si se puede añadir.
     */
    public int addToColumna(int col) {
        int noEncontrado = -1;
        for (int i = 0; i < filas; i++) {
            if (casillas[i][col] == VACIO) {
                casillas[i][col] = turno;
                cambiarTurno();
                return i;
            }
        }
        return noEncontrado;
    }

    /**
        Funcion que cambia el turno de Rojo a Amarillo y viceversa
     */
    private void cambiarTurno() {
        if (turno == ROJO) {
            turno = AMARILLO;
        } else {
            turno = ROJO;
        }
    }

    /**
        Funcion que comprueba si hay lineas de 4 fichas en todo el tablero
     */
    public boolean hayGanador() {
        return (lineaHorizontal() || lineaVertical() || lineaDiagonalDerecha() || lineaDiagonalIzquierda());
    }

    /**
        Funcion que recorre el tablero y si encuentra una casilla sin ficha 
        no hay empate, en caso contrario puede haber empate
     */
    public boolean empate() {
        for (int[] i : casillas) {
            for (int j : i) {
                if (j == VACIO) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
        Getter de turno
     */
    public int getTurno() {
        return turno;
    }

    /**
        Funcion que devuelve si hay 4 fichas horizontales seguidas
     */
    private boolean cuatroEnFila(int fila, int maximo) {
        int anterior = -1;
        int contador = 0;
        for (int j = 0; j < maximo; j++) {
            if (casillas[fila][j] == anterior) {
                contador++;
            } else {
                contador = 0;
            }
            anterior = casillas[fila][j];
            if (contador == 3) {
                return true;
            }
        }
        return false;
    }

    /**
        Funcion que comprueba cuatroEnFila para todo el tablero
     */
    private boolean lineaHorizontal() {
        for (int i = 0; i < filas; i++) {
            if (casillas[i][3] != VACIO && cuatroEnFila(i, columnas)) {
                return true;
            }
        }
        return false;
    }

    /**
        Funcion que devuelve si hay 4 fichas verticales seguidas
     */
    private boolean cuatroEnColumna(int columna, int maximo) {
        int anterior = -1;
        int contador = 0;
        for (int j = 0; j < maximo; j++) {
            if (casillas[j][columna] == anterior) {
                contador++;
            } else {
                contador = 0;
            }
            anterior = casillas[j][columna];
            if (contador == 3) {
                return true;
            }
        }
        return false;
    }

    /**
        Funcion que comprueba cuatroEnColumna para todo el tablero
     */
    private boolean lineaVertical() {
        for (int i = 0; i < columnas; i++) {
            if (casillas[2][i] == casillas[3][i] && casillas[2][i] != VACIO && casillas[3][i] != VACIO) {
                if (cuatroEnColumna(i, filas)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
        Funcion que devuelve si hay 4 fichas diagonales a la derecha seguidas
     */
    private boolean cuatroEnDDerecha(int fila, int col, int max) {
        int anterior = -1;
        int contador = 0;
        for (int i = 0; i < max; i++) {
            if (casillas[fila + i][col + i] == anterior) {
                contador++;
            } else {
                contador = 0;
            }
            anterior = casillas[fila + i][col + i];
            if (contador == 3) {
                return true;
            }
        }
        return false;
    }

    /**
        Funcion que comprueba cuatroEnDDerecha para todo el tablero
     */
    private boolean lineaDiagonalDerecha() {
        if (casillas[2][0] != VACIO && casillas[2][0] == casillas[3][1] && casillas[2][0] == casillas[4][2] && casillas[2][0] == casillas[5][3]) {
            return true;
        }
        if (casillas[0][3] != VACIO && casillas[0][3] == casillas[1][4] && casillas[0][3] == casillas[2][5] && casillas[0][3] == casillas[3][6]) {
            return true;
        }
        if (casillas[2][1] != VACIO && casillas[3][2] != VACIO && casillas[4][3] != VACIO) {
            if (cuatroEnDDerecha(1, 0, 5)) {
                return true;
            }
        }
        if (casillas[1][3] != VACIO && casillas[2][4] != VACIO && casillas[3][5] != VACIO) {
            if (cuatroEnDDerecha(0, 2, 5)) {
                return true;
            }
        }
        if (casillas[2][2] != VACIO && casillas[3][3] != VACIO) {
            if (cuatroEnDDerecha(0, 0, 6)) {
                return true;
            }
        }
        if (casillas[2][3] != VACIO && casillas[3][4] != VACIO) {
            if (cuatroEnDDerecha(0, 1, 6)) {
                return true;
            }
        }
        return false;
    }

    /**
        Funcion que devuelve si hay 4 fichas diagonales a la izquierda seguidas
     */
    private boolean cuatroEnDIzquierda(int fila, int col, int max) {

        int anterior = -1;
        int contador = 0;
        for (int i = 0; i < max; i++) {
            if (casillas[fila - i][col + i] == anterior) {
                contador++;
            } else {
                contador = 0;
            }
            anterior = casillas[fila - i][col + i];
            if (contador == 3) {
                return true;
            }
        }
        return false;
    }

    /**
        Funcion que comprueba cuatroEnDIzquierda para todo el tablero
     */
    private boolean lineaDiagonalIzquierda() {
        if (casillas[3][0] != VACIO && casillas[3][0] == casillas[2][1] && casillas[2][1] == casillas[1][2] && casillas[1][2] == casillas[0][3]) {
            return true;
        }
        if (casillas[5][3] != VACIO && casillas[5][3] == casillas[4][4] && casillas[4][4] == casillas[3][5] && casillas[3][5] == casillas[2][6]) {
            return true;
        }
        if (casillas[3][1] != VACIO && casillas[2][2] != VACIO && casillas[1][3] != VACIO) {
            if (cuatroEnDIzquierda(4, 0, 5)) {
                return true;
            }
        }
        if (casillas[4][3] != VACIO && casillas[3][4] != VACIO && casillas[2][5] != VACIO) {
            if (cuatroEnDIzquierda(5, 2, 5)) {
                return true;
            }
        }
        if (casillas[2][4] != VACIO && casillas[3][3] != VACIO) {
            if (cuatroEnDIzquierda(5, 1, 6)) {
                return true;
            }
        }
        if (casillas[2][3] != VACIO && casillas[3][2] != VACIO) {
            if (cuatroEnDIzquierda(5, 0, 6)) {
                return true;
            }
        }
        return false;
    }

}
