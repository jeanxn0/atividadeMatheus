using System;
using Minhabiblioteca;
class Exercicio8
{
    static int contarRaiosMesmoLocal(int[,] matriz)
    {
        int cont = 0;
        int linhas = matriz.GetLength(0);
        int cols = matriz.GetLength(1);
        for (int i = 0; i < linhas; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                if (matriz[i, j] > 1)
                {
                    cont++;
                }
            }
        }
        return 0;
    }
    static void Main()
    {
        int x, y, quantidadeRaios;
        int linhas, cols;
        Console.WriteLine("Entre com a quantidade de linhas e colunas da area:");
        linhas = int.Parse(Console.ReadLine());
        cols = int.Parse(Console.ReadLine());
        int[,] mapa = new int[linhas, cols];
        Console.WriteLine("Raios Anotados:");
        quantidadeRaios = int.Parse(Console.ReadLine());
        for (int i = 0; i < quantidadeRaios; i++)
        {
            Console.WriteLine("Coordenadas que caiu o raio:");
            x = int.Parse(Console.ReadLine());
            y = int.Parse(Console.ReadLine());
            mapa[x, y]++;

        }
        Minhabiblioteca.Biblioteca.mostrarMatriz(mapa);

    }
}