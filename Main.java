//Emiliano Parra Varela - 00609042

public class Main{
    public static void main(String[] args) {
        int INF = 999999;
        int N = 6; // Vértices: A=0, B=1, C=2, D=3, E=4, F=5[cite: 7]
        int[] dist = new int[N];
        int[] pred = new int[N];
        boolean[] visitado = new boolean[N];
        int[][] mat = new int[N][N];

        // Inicializar matriz de adyacencia con INF[cite: 7]
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                mat[i][j] = INF;
            }
        }

        // Cargar aristas (A=0, B=1, C=2, D=3, E=4, F=5)[cite: 7]
        mat[0][1] = 4; mat[1][0] = 4; // A-B: 4[cite: 7]
        mat[0][2] = 2; mat[2][0] = 2; // A-C: 2[cite: 7]
        mat[1][2] = 1; mat[2][1] = 1; // B-C: 1[cite: 7]
        mat[1][3] = 5; mat[3][1] = 5; // B-D: 5[cite: 7]
        mat[2][4] = 10; mat[4][2] = 10; // C-E: 10[cite: 7]
        mat[3][4] = 3; mat[4][3] = 3; // D-E: 3[cite: 7]
        mat[3][5] = 2; mat[5][3] = 2; // D-F: 2[cite: 7]
        mat[4][5] = 1; mat[5][4] = 1; // E-F: 1[cite: 7]

        // Ejecutar el algoritmo de Dijkstra con origen en A (índice 0)
        dijkstra(mat, dist, visitado, pred, 0, N, INF);

        // Mostrar resultados y camino óptimo hacia F (índice 5)
        mostrarResultados(dist, pred, 5, N);
    }

    public static void dijkstra(int[][] mat, int[] dist, boolean[] visitado, int[] pred, int origen, int N, int INF) {
        for (int i = 0; i < N; i++) {
            dist[i] = INF;
            visitado[i] = false;
            pred[i] = -1;
        }
        dist[origen] = 0;

        for (int i = 0; i < N; i++) {
            int u = -1;
            int minDist = INF;
            
            // Seleccionar el vértice no visitado con la menor distancia acumulada[cite: 2]
            for (int j = 0; j < N; j++) {
                if (!visitado[j] && dist[j] < minDist) {
                    minDist = dist[j];
                    u = j;
                }
            }

            if (u == -1) {
                break;
            }

            visitado[u] = true;

            // Relajación de aristas[cite: 2]
            for (int j = 0; j < N; j++) {
                if (!visitado[j] && mat[u][j] < INF) {
                    if (dist[u] + mat[u][j] < dist[j]) {
                        dist[j] = dist[u] + mat[u][j];
                        pred[j] = u;
                    }
                }
            }
        }
    }

    public static void mostrarResultados(int[] dist, int[] pred, int destino, int N) {
        char[] nombres = {'A', 'B', 'C', 'D', 'E', 'F'};

        System.out.println("=== Distancias minimas desde A ===");
        for (int i = 0; i < N; i++) {
            System.out.println(nombres[0] + " -> " + nombres[i] + " = " + dist[i]);
        }
        System.out.println();

        // Reconstrucción del camino óptimo usando el arreglo pred[][cite: 4, 5]
        int[] camino = new int[N];
        int k = 0;
        int actual = destino;
        while (actual != -1) {
            camino[k++] = actual;
            actual = pred[actual];
        }

        System.out.print("Camino optimo: ");
        for (int i = k - 1; i >= 0; i--) {
            if (i > 0) {
                System.out.print(nombres[camino[i]] + " -> ");
            } else {
                System.out.print(nombres[camino[i]]);
            }
        }
        System.out.println();
        System.out.println("Distancia total: " + dist[destino]);
    }
}