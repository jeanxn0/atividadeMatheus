
using System;
class Exercicio9
{
    static void Main()
    {
        int n;
        Console.Write("Entre com o tamanho do vetor: ");
        n = int.Parse(Console.ReadLine());
        char[] vetor = new char[n];
        Console.WriteLine("Entre com os caracteres do vetor:");
        for (int i = 0; i < n; i++)
        {
            Console.Write($"Caractere {i + 1}: ");
            vetor[i] = Console.ReadLine()[0];
        }
        Console.WriteLine("Vetor em ordem inversa:");
        for (int i = n - 1; i >= 0; i--)
        {
            Console.Write(vetor[i] + " ");
        }
        Console.WriteLine();
    }
}