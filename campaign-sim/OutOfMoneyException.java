public class OutOfMoneyException extends Exception
{
     public String getMessage()
     {
          return "Advert can't be generated, Candidate lacks funds.";
     }
}