using System;
using MinhaBiblioteca;

class Exercicio3
{
    static  int menorVetor(int[] vetor)
    {
        int menor = vetor[0];
        for (int i = 1; i < vetor.Length; i++)
        {
            if (vetor[i] < menor)
            {
                menor = vetor[i];
            }
        }
        return menor;
    }
        static void Main()
    {
        int n;
        Console.Write("tamanho do vetor: ");
        n = int.Parse(Console.ReadLine());
        int[] Vetor = new int[n];
        Biblioteca.gerarVetor(Vetor);
        Biblioteca.mostrarVetor(Vetor);
        int menor = menorVetor(Vetor);
        Console.WriteLine("Menor valor do vetor: " + menor);
        Console.ReadKey();
    }
}