using System;
using MinhaBiblioteca;
class Exercicio6
{
    static bool buscarNumero(int[] vetor, int numero, out int posicao)
    {
        for (int i = 0; i < vetor.Length; i++)
        {
            if (vetor[i] == numero)
            {
                posicao = i;
                return true;
            }
        }
        posicao = -1;
        return false;
    }
    static void Main()
    {
        int n, numero, posicao;
        Console.Write("Entre com o tamanho do vetor: ");
        n = int.Parse(Console.ReadLine());
        int[] meuVetor = new int[n];
        Biblioteca.gerarVetor(meuVetor);
        Biblioteca.mostrarVetor(meuVetor);
        Console.Write("Entre com o número a ser buscado: ");
        numero = int.Parse(Console.ReadLine());
        if (buscarNumero(meuVetor, numero, out posicao))
            Console.WriteLine($"Número {numero} encontrado na posição {posicao}.");
        else
            Console.WriteLine($"Número {numero} não encontrado no vetor.");

    }

}