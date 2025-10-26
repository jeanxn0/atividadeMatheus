using System;
class Exercicio5DNA
{
    static string completarDNA(string dna)
    {
        int n = dna.Length;
        dna = dna.ToUpper();
        char[] complemento = new char[n];
        for (int i = 0; i < n; i++)
        {
            switch (dna[i])
            {
                case 'A': complemento[i] = 'T';
                    break;
                case 'T': complemento[i] = 'A';
                    break;
                case 'C': complemento[i] = 'G';
                    break;
                case 'G': complemento[i] = 'C';
                    break;
                default:
                    complemento[i] = 'N'; break; // N para qualquer base desconhecida
                    break;
            }
        }
        return new string(complemento);
    }
    static void Main()
    {
        string dna;
        Console.Write("Entre com a fita de DNA: ");
        dna = Console.ReadLine();
        //string comp = completarDNA(dna);
        //Console.WriteLine("Fita complementar: " + comp);
        Console.WriteLine("Fita complementar: " + completarDNA(dna));

    }
   
}