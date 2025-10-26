using System;
using MinhaBiblioteca;

class Exercicio1
{
    static int somaVetor(int[] vetor)
    {
        int soma = 0;
        for (int i = 0; i < vetor.Length; i++)
        {
            soma += vetor[i];
        }
        return soma;
    }
    static void Main()
    {
        int n;
        Console.WriteLine("Tamanho do vetor:");
        n = int.Parse(Console.ReadLine());
        int[] vetor = new int[n];
        Biblioteca.gerarVetor(vetor);
        Biblioteca.mostrarVetor(vetor);
        int soma = somaVetor(vetor);
        Console.WriteLine("A soma do vetor é: " + soma);
        Console.ReadKey();
    }
}