using System;
class Exercicio10
{
    static void Main()
    {
        int n;
        Console.Write("Entre com o número de lançamentos do dado: ");
        n = int.Parse(Console.ReadLine());
        int[] vetor = new int[n];
        Console.WriteLine("Entre com os resultados dos lançamentos :");
        for (int i = 0; i < n; i++)
        {
            Console.Write($"Lançamento {i + 1}: ");
            vetor[i] = int.Parse(Console.ReadLine());
        }
        int[] ocorrencias = new int[6]; // Para armazenar as ocorrências de cada face (1 a 6)
        for (int i = 0; i < n; i++)
        {
            if (vetor[i] >= 1 && vetor[i] <= 6)
            {
                ocorrencias[vetor[i] - 1]++;
            }
        }
        Console.WriteLine("Número de ocorrências de cada face:");
        for (int i = 0; i < 6; i++)
        {
            Console.WriteLine($"Face {i + 1}: {ocorrencias[i]} vezes");
        }
    }
}