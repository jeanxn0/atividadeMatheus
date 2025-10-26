
using System;
using MinhaBiblioteca;
class Exercicio8
{
    static int contarOcorrencias(int[] vetor, int valor)
    {
        int contador = 0;
        for (int i = 0; i < vetor.Length; i++)
        {
            if (vetor[i] == valor)
            {
                contador++;
            }
        }
        return contador;
    }
    static void Main()
    {
        int n, valor, ocorrencias;
        Console.Write("Entre com o tamanho do vetor: ");
        n = int.Parse(Console.ReadLine());
        int[] meuVetor = new int[n];
        Biblioteca.gerarVetor(meuVetor);
        Biblioteca.mostrarVetor(meuVetor);
        Console.Write("Digite o valor a ser verificado: ");
        valor = int.Parse(Console.ReadLine());
        ocorrencias = contarOcorrencias(meuVetor, valor);
        Console.WriteLine($"O valor {valor} ocorre {ocorrencias} vezes no vetor.");
    }

}