using System;
using MinhaBiblioteca;

class Exercicio4
{
    static  int maiorVetor(int[] vetor)
    {
        int maior = vetor[0];
        for (int i = 1; i < vetor.Length; i++)
        {
            if (vetor[i] > maior)
            {
                maior = vetor[i];
            }
        }
        return maior;
    }
    static void Main()
    {
        int n;
        Console.Write("tamanho do vetor: ");
        n = int.Parse(Console.ReadLine());
        int[] Vetor = new int[n];
        Biblioteca.gerarVetor(Vetor);
        Biblioteca.mostrarVetor(Vetor);
        int maior = maiorVetor(Vetor);
        Console.WriteLine("Maior valor do vetor: " + maior);
        Console.ReadKey();
    }
}

        
    