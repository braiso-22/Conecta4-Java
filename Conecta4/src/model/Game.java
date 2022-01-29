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

    private void iniciarCasillas() {
        for (int i = filas - 1; i > 0; i--) {
            for (int j = 0; j < columnas; j++) {
                casillas[i][j] = VACIO;
            }
        }
    }

    public boolean addToColumna(int col) {
        boolean encontrado = false;
        for (int i = 0; i < filas && !encontrado; i++) {

            if (casillas[i][col] == VACIO) {
                casillas[i][col] = turno;
                cambiarTurno();
                encontrado = true;
            }
        }
        return encontrado;
    }

    private void cambiarTurno() {
        if (turno == ROJO) {
            turno = AMARILLO;
        } else {
            turno = ROJO;
        }
    }

    public boolean finalizado() {
        return true;
    }

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

    private boolean lineaHorizontal() {
        for (int i = 0; i < filas; i--) {
            if (casillas[i][3] != VACIO && cuatroEnFila(i, columnas)) {
                return true;
            }
        }
        return false;
    }

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

    private boolean lineaVertical() {
        for (int i = 0; i < columnas; i--) {
            if (casillas[2][i] == casillas[3][i] && casillas[2][i] != VACIO && casillas[3][i] != VACIO) {
                if (cuatroEnColumna(i, filas)) {
                    return true;
                }
            }
        }
        return false;
    }

}
