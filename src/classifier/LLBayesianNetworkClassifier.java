package classifier;

import dataset.Attribute;

public class LLBayesianNetworkClassifier extends AbstractBayesianNetworkClassifier {

	public LLBayesianNetworkClassifier() {
		super();
	}

	/**
	 * 
	 * @param i
	 * @param j
	 */
	protected float computeWeight(Attribute i, Attribute _j) {
		float alpha = 0;
		int q_i = trainSet.getMaxAttributeValue(_j);
		int r_i = trainSet.getMaxAttributeValue(i);
		int s = trainSet.getMaxClassValue();
		
		for(int c = 0; c <= s; c++)
		{
			int Nc = computeNc(c);
			for(int j = 0; j <= q_i; j++)
			{
				int Nijc_K = computeNijc_K(i,j,c);
				for(int k = 0; k <= r_i; k++)
				{
					int Nikc_J = computeNikc_J(i,k,c);
					int Nijkc = computeNijkc(i,j,k,c);
					if(Nijkc != 0)
						alpha += Nijkc/computeN() * log2((float)(Nijkc*Nc)/(Nikc_J*Nijc_K));
					
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