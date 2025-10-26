using System;
using MinhaBiblioteca;
class Exercicio7
{
    static void Main()
    {
        int n;
        Console.Write("Entre com o tamanho do vetor: ");
        n = int.Parse(Console.ReadLine());
        int[] vetorA = new int[n];
        int[] vetorB = new int[n];
        int[] vetorC = new int[n];
        Biblioteca.gerarVetor(vetorA);
        Biblioteca.gerarVetor(vetorB);
        for (int i = 0; i < n; i++)
        {
            vetorC[i] = vetorA[i] * vetorB[i];
        }
        Console.WriteLine("Vetor A:");
        Biblioteca.mostrarVetor(vetorA);
        Console.WriteLine("Vetor B:");
        Biblioteca.mostrarVetor(vetorB);
        Console.WriteLine("Vetor C:");
        Biblioteca.mostrarVetor(vetorC);
    }
   
}