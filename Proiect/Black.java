import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Black {

    //Functie pentru Crazy-House
    public static void moveFromCrazyHouseForBlack(Move move, LinkedHashMap<Character, Integer> blackCrazyHouse, char[][] chess ) {
        Piece chosen = move.getReplacement().get();
        char chosenPiece = '-';

        if (chosen == Piece.PAWN) {
            chosenPiece = 'P';
            Bot.wantToChoose(chosenPiece, blackCrazyHouse);
        }

        if (chosen == Piece.QUEEN) {
            chosenPiece = 'Q';
            Bot.wantToChoose(chosenPiece, blackCrazyHouse);
        }

        if (chosen == Piece.BISHOP) {
            chosenPiece = 'B';
            Bot.wantToChoose(chosenPiece, blackCrazyHouse);
        }

        if (chosen == Piece.KNIGHT) {
            chosenPiece = 'N';
            Bot.wantToChoose(chosenPiece, blackCrazyHouse);
        }

        if (chosen == Piece.ROOK) {
            chosenPiece = 'R';
            Bot.wantToChoose(chosenPiece, blackCrazyHouse);
        }

        String dest = move.getDestination().get();
        int destinationX = 7 - dest.charAt(1) + 49;
        int destinationY = dest.charAt(0) - 97;
        chess[destinationX][destinationY] = chosenPiece;

        //Bot.printOutput(move);
    }

    //Functie pentru a actualiza tabla din programul nostru

    public static void moveBlack(Move move, char[][] chess, LinkedHashMap<Character, Integer> whiteCrazyHouse, LinkedHashMap<Character, Integer> blackCrazyHouse) {
        // Sursa piesei(de unde pleaca)
        String src = move.getSource().get();
        // Destinatia piesei(unde ajunge)
        String dest = move.getDestination().get();

        // Coordonatele pe "Ox" si "Oy" ale sursei piesei curente.
        int sourceX = 7 - src.charAt(1) + 49;
        int sourceY = src.charAt(0) - 97;
        // Coordonatele pe "Ox" si "Oy" ale destinatiei piesei curente.
        int destinationX = 7 - dest.charAt(1) + 49;
        int destinationY = dest.charAt(0) - 97;

        if(sourceX == 0 && sourceY == 0)
            Bot.blackCastlingLong = 1;
        if(sourceX == 0 && sourceY == 7)
            Bot.blackCastlingShort = 1;
        if(sourceX == 0 && sourceY == 4)
            Bot.blackKing = 1;



        // Piesa ce dorim sa o mutam la destinatie, plecand din sursa
        char piece = chess[sourceX][sourceY];
        // Initial avem casuta libera la destinatie
        chess[sourceX][sourceY] = '-';

        char putForBlack = (char)(chess[destinationX][destinationY] - 32);
        // Modul "Crazy house" pentru piesele negre(amplasarea in dictionar dupa ce piesa a fost luata)
        if(putForBlack + 32 != '-' && blackCrazyHouse.containsKey(putForBlack)) {
            blackCrazyHouse.put(putForBlack, blackCrazyHouse.get(putForBlack) + 1);
        } else {
            blackCrazyHouse.put(putForBlack, 1);
        }

        // Destinatia va deveni piesa pe care am mutat-o
        chess[destinationX][destinationY] = piece;

        // Pionul a ajuns in terenul adversar(ultima linie din matrice plecand dinspre negru)
        if (7 - destinationX == 0 && chess[destinationX][destinationY] == 'P') {
            chess[destinationX][destinationY] = 'L';
        }

        // Rocada mare
        if (sourceY - destinationY == 2 && piece == 'K') {
            chess[destinationX][destinationY + 1] = 'R';
            chess[destinationX][destinationY - 2] = '-';
            Bot.doneCastlingBlack = 1;
        }

        // Rocada mica
        if (sourceY - destinationY == -2 && piece == 'K') {
            chess[destinationX][destinationY - 1] = 'R';
            chess[destinationX][destinationY + 1] = '-';
            Bot.doneCastlingBlack = 1;
        }
    }

    //functie pentru pion

    public static void find_pawn_moves(int src_x, int src_y, ArrayList<Position> white, ArrayList<Position> black, char[][] chess) {

        int up_black = src_x + 1;
        int up_black_two = src_x + 2;
        int left_black = src_y + 1;
        int right_black = src_y - 1;

        if (src_x == 1) {
            if (chess[up_black][src_y] == '-') {
                black.add(new Position(src_x, src_y, up_black, src_y));
                if (chess[up_black_two][src_y] == '-') {
                    black.add(new Position(src_x, src_y, up_black_two, src_y));
                }
            }

            if (left_black < 8 && up_black < 8 && chess[up_black][left_black] != '-' && chess[up_black][left_black] > 'a' && chess[up_black][left_black] < 'z') {
                black.add(new Position(src_x, src_y, up_black, left_black));
            }
            if (right_black >= 0 && up_black < 8 && chess[up_black][right_black] != '-' && chess[up_black][right_black] > 'a' && chess[up_black][right_black] < 'z') {
                black.add(new Position(src_x, src_y, up_black, right_black));
            }
        } else {
            if (up_black < 8 && chess[up_black][src_y] == '-') {
                black.add(new Position(src_x, src_y, up_black, src_y));
            }
            if (left_black < 8 && up_black < 8 && chess[up_black][left_black] != '-' && chess[up_black][left_black] > 'a' && chess[up_black][left_black] < 'z') {
                black.add(new Position(src_x, src_y, up_black, left_black));
            }
            if (right_black >= 0 && up_black < 8 && chess[up_black][right_black] != '-' && chess[up_black][right_black] > 'a' && chess[up_black][right_black] < 'z') {
                black.add(new Position(src_x, src_y, up_black, right_black));
            }
        }
    }

    public static void find_king_moves(int src_x, int src_y, ArrayList<Position> white, ArrayList<Position> black, char[][] chess) {
        int up = src_x + 1;
        int down = src_x - 1;
        int left = src_y + 1;
        int right = src_y - 1;

        if (up < 8 && (chess[up][src_y] < 'A' || chess[up][src_y] > 'Z'))
            black.add(new Position(src_x, src_y, up, src_y));
        if (down >= 0 && (chess[down][src_y] < 'A' || chess[down][src_y] > 'Z'))
            black.add(new Position(src_x, src_y, down, src_y));
        if (left < 8 && (chess[src_x][left] < 'A' || chess[src_x][left] > 'Z'))
            black.add(new Position(src_x, src_y, src_x, left));
        if (right >= 0 && (chess[src_x][right] < 'A' || chess[src_x][right] > 'Z'))
            black.add(new Position(src_x, src_y, src_x, right));
        if (up < 8 && left < 8 && (chess[up][left] < 'A' || chess[up][left] > 'Z'))
            black.add(new Position(src_x, src_y, up, left));
        if (up < 8 && right >= 0 && (chess[up][right] < 'A' || chess[up][right] > 'Z'))
            black.add(new Position(src_x, src_y, up, right));
        if (down >= 0 && left < 8 && (chess[down][left] < 'A' || chess[down][left] > 'Z'))
            black.add(new Position(src_x, src_y, down, left));
        if (down >= 0 && right >= 0 && (chess[down][right] < 'A' || chess[down][right] > 'Z'))
            black.add(new Position(src_x, src_y, down, right));
    }

    public static void find_bishop_moves(int src_x, int src_y, ArrayList<Position> white, ArrayList<Position> black, char[][] chess){
        ///diag-principala sus
        for (int i = 1; i < 8; i++) {
            if (src_x - i >= 0 && src_y - i >= 0 && chess[src_x - i][src_y - i] == '-')///liber
                black.add(new Position(src_x, src_y, src_x - i, src_y - i));
            else if (src_x - i >= 0 && src_y - i >= 0 && chess[src_x - i][src_y - i] > 'a' && chess[src_x - i][src_y - i] < 'z') { /// piesa adversa
                black.add(new Position(src_x, src_y, src_x - i, src_y - i));
                break;
            } else
                break;

        }
        ///diag-principala jos
        for (int i = 1; i < 8; i++) {
            if (src_x + i < 8 && src_y + i < 8 && chess[src_x + i][src_y + i] == '-')///liber
                black.add(new Position(src_x, src_y, src_x + i, src_y + i));
            else if (src_x + i < 8 && src_y + i < 8 && chess[src_x + i][src_y + i] > 'a' && chess[src_x + i][src_y + i] < 'z') { /// piesa adversa
                black.add(new Position(src_x, src_y, src_x + i, src_y + i));
                break;
            } else
                break;

        }
        ///diag-secundara sus
        for (int i = 1; i < 8; i++) {
            if (src_x - i >= 0 && src_y + i < 8 && chess[src_x - i][src_y + i] == '-')///liber
                black.add(new Position(src_x, src_y, src_x - i, src_y + i));
            else if (src_x - i >= 0 && src_y + i < 8 && chess[src_x - i][src_y + i] > 'a' && chess[src_x - i][src_y + i] < 'z') { /// piesa adversa
                black.add(new Position(src_x, src_y, src_x - i, src_y + i));
                break;
            } else
                break;

        }

        ///diag-secundara jos
        for (int i = 1; i < 8; i++) {
            if (src_x + i < 8 && src_y - i >= 0 && chess[src_x + i][src_y - i] == '-')///liber
                black.add(new Position(src_x, src_y, src_x + i, src_y - i));
            else if (src_x + i < 8 && src_y - i >= 0 && chess[src_x + i][src_y - i] > 'a' && chess[src_x + i][src_y - i] < 'z') { /// piesa adversa
                black.add(new Position(src_x, src_y, src_x + i, src_y - i));
                break;
            } else
                break;

        }
    }

    public static void find_rook_moves(int src_x, int src_y, ArrayList<Position> white, ArrayList<Position> black, char[][] chess){
        // rulam un for prin care adaugam toate miscarile in fata pana cand
        // gaseste un loc ocupat sau iese de pe tabla si oprim for-ul
        for (int i = src_x - 1; i >= 0; i--) {
            if (chess[i][src_y] == '-') {
                black.add(new Position(src_x, src_y, i, src_y));
            } else if (chess[i][src_y] >= 'a' && chess[i][src_y] <= 'z') {
                black.add(new Position(src_x, src_y, i, src_y));
                break;
            } else {
                break;
            }
        }
        // rulam un for prin care adaugam toate miscarile in spate pana cand
        // gaseste un loc ocupat sau iese de pe tabla si oprim for-ul
        for (int i = src_x + 1; i <= 7; i++) {
            if (chess[i][src_y] == '-') {
                black.add(new Position(src_x, src_y, i, src_y));
            } else if (chess[i][src_y] >= 'a' && chess[i][src_y] <= 'z') {
                black.add(new Position(src_x, src_y, i, src_y));
                break;
            } else {
                break;
            }
        }
        // rulam un for prin care adaugam toate miscarile la stanga pana cand
        // gaseste un loc ocupat sau iese de pe tabla si oprim for-ul
        for (int i = src_y - 1; i >= 0; i--) {
            if (chess[src_x][i] == '-') {
                black.add(new Position(src_x, src_y, src_x, i));
            } else if (chess[src_x][i] >= 'a' && chess[src_x][i] <= 'z') {
                black.add(new Position(src_x, src_y, src_x, i));
                break;
            } else {
                break;
            }
        }
        // rulam un for prin care adaugam toate miscarile la dreapta pana cand
        // gaseste un loc ocupat sau iese de pe tabla si oprim for-ul
        for (int i = src_y + 1; i <= 7; i++) {
            if (chess[src_x][i] == '-') {
                black.add(new Position(src_x, src_y, src_x, i));
            } else if (chess[src_x][i] >= 'a' && chess[src_x][i] <= 'z') {
                black.add(new Position(src_x, src_y, src_x, i));
                break;
            } else {
                break;
            }
        }
    }
}