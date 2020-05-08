package classifier;

import dataset.Attribute;

public class LLBayesianNetworkClassifier extends AbstractBayesianNetworkClassifier {

	/**
	 * Creates a Bayesian Network Classifier that uses the log-likelihood (LL)
	 * scoring criteria
	 */
	public LLBayesianNetworkClassifier() {
		super();
	}
	
	/**
	 * Computes the LL weight between two attributes
	 * @param i first attribute
	 * @param i_prime second attribute
	 * @return weight
	 */
	@Override
	protected float computeWeight(Attribute i, Attribute i_prime) {
		float alpha = 0;
		int q_i = trainSet.getMaxAttributeValue(i_prime) + 1;
		int r_i = trainSet.getMaxAttributeValue(i) + 1;
		int s = trainSet.getMaxClassValue() + 1;
		int N = computeN();
		for(int c = 0; c <= s-1; c++)
		{
			int Nc = computeNc(c);
			for(int j = 0; j <= q_i-1; j++)
			{
				int Nijc_K = computeNikc(i_prime,j,c);
				for(int k = 0; k <= r_i-1; k++)
				{
					int Nikc_J = computeNikc(i,k,c);
					int Nijkc = computeNijkc(i,i_prime,j,k,c);
					if(Nijkc != 0)
						alpha += (float)Nijkc/N * log2((float)(Nijkc*Nc)/(Nikc_J*Nijc_K));
					
				}
			}
		}
		
		return alpha;
		
	}
	
	private float log2(float i)
	{
		return (float) (Math.log(i)/Math.log(2));
	}
	
	
	

	

}