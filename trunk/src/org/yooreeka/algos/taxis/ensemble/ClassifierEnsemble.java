package org.yooreeka.algos.taxis.ensemble;

import java.util.ArrayList;
import java.util.List;

import org.yooreeka.algos.taxis.core.intf.Classifier;
import org.yooreeka.algos.taxis.core.intf.Concept;
import org.yooreeka.algos.taxis.core.intf.Instance;

/**
 * Base implementation for bagging classifier.
 */
public abstract class ClassifierEnsemble implements Classifier {

    protected String name;
    
    protected boolean verbose = false;
    
    protected List<Classifier> baseClassifiers = new ArrayList<Classifier>();
    
    public ClassifierEnsemble(String name) {
        this.name = name;
    }
    
    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }
    
    public Concept classify(Instance instance) {

        ConceptMajorityVoter voter = new ConceptMajorityVoter(instance);
        
        for( Classifier baseClassifier : baseClassifiers ) {
            
            Concept c = baseClassifier.classify(instance);
            
            voter.addVote(c);
        }
        
        if( verbose ) {
            voter.print();
        }
        
        return voter.getWinner();
    }

    public String getName() {
        return name;
    }

    public boolean train() {
        
        for( Classifier c : baseClassifiers ) {
            // training base classifier
            c.train();
        }
        
        return true;
    }
    
    public int getEnsemblePopulation() {
    	return baseClassifiers.size();
    }
    
    public void addMember(Classifier baseClassifier) {
        baseClassifiers.add(baseClassifier);
    }
    
    public void removeMember(Classifier c) {
    	baseClassifiers.remove(c);
    }
    
    public enum ClassifierMemberType {
    	NEURAL_NETWORK,
    	DECISION_TREE,
    	NAIVE_BAYES
    }
}
