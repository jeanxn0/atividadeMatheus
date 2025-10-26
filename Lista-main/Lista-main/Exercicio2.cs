using System;
using MinhaBiblioteca;

class Exercicio2
{
    static int contadorImpar(int[] vetor)
    {

        int contador = 0;
        for (int i = 0; i < vetor.Length; i++)
        {
            if (vetor[i] % 2 != 0)
                contador++;
        }
        return contador;

    }
    static void Main()
    {
        int n;
        Console.WriteLine("Tamanho do vetor:");
        n = int.Parse(Console.ReadLine());
        int[] vetor = new int[n];
        Biblioteca.gerarVetor(vetor);
        Biblioteca.mostrarVetor(vetor);
        int qtdImpar = contadorImpar(vetor);
        Console.WriteLine($"Os numeros impares sao :" + qtdImpar);
        Console.ReadKey();

    }

}