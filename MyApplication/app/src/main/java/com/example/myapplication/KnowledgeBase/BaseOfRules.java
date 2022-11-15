package com.example.myapplication.KnowledgeBase;

import com.github.cschen1205.ess.engine.EqualsClause;
import com.github.cschen1205.ess.engine.KieRuleInferenceEngine;
import com.github.cschen1205.ess.engine.Rule;
import com.github.cschen1205.ess.engine.RuleInferenceEngine;

public class BaseOfRules {
    public RuleInferenceEngine getInferenceEngine()
    {
        RuleInferenceEngine rie=new KieRuleInferenceEngine();

        Rule rule=new Rule("There is high chances getting on budget in CS (200)");
        rule.addAntecedent(new EqualsClause("Your ukrainian language score", "200"));
        rule.addAntecedent(new EqualsClause("Was maths exam?", "yes"));
        rule.addAntecedent(new EqualsClause("Your maths score", "200"));
        rule.addAntecedent(new EqualsClause("Was foreign language exam?", "yes"));
        rule.addAntecedent(new EqualsClause("Your foreign language score", "200"));
        rule.addAntecedent(new EqualsClause("University", "UMSF"));
        rule.setConsequent(new EqualsClause("Your chances:","high"));
        rie.addRule(rule);

        rule=new Rule("There is no chances getting on budget in CS (200)");
        rule.addAntecedent(new EqualsClause("Your ukrainian language score", "200"));
        rule.addAntecedent(new EqualsClause("Was maths exam?", "no"));
        rule.addAntecedent(new EqualsClause("Your maths score", "200"));
        rule.addAntecedent(new EqualsClause("Was foreign language exam?", "no"));
        rule.addAntecedent(new EqualsClause("Your foreign language score", "200"));
        rule.addAntecedent(new EqualsClause("University", "UMSF"));
        rule.setConsequent(new EqualsClause("Your chances:","no chance"));
        rie.addRule(rule);

        rule=new Rule("There is high chances getting on budget in CS");
        rule.addAntecedent(new EqualsClause("Your ukrainian language score", "190"));
        rule.addAntecedent(new EqualsClause("Was maths exam?", "yes"));
        rule.addAntecedent(new EqualsClause("Your maths score", "190"));
        rule.addAntecedent(new EqualsClause("Was foreign language exam?", "yes"));
        rule.addAntecedent(new EqualsClause("Your foreign language score", "190"));
        rule.addAntecedent(new EqualsClause("University", "UMSF"));
        rule.setConsequent(new EqualsClause("Your chances:","high"));
        rie.addRule(rule);

        rule=new Rule("There is above average chances getting on budget in CS (180)");
        rule.addAntecedent(new EqualsClause("Your ukrainian language score", "180"));
        rule.addAntecedent(new EqualsClause("Was maths exam?", "v"));
        rule.addAntecedent(new EqualsClause("Your maths score", "180"));
        rule.addAntecedent(new EqualsClause("Was foreign language exam?", "yes"));
        rule.addAntecedent(new EqualsClause("Your foreign language score", "180"));
        rule.addAntecedent(new EqualsClause("University", "UMSF"));
        rule.setConsequent(new EqualsClause("Your chances:","above average"));
        rie.addRule(rule);

        rule=new Rule("There is average chances getting on budget in CS (170)");
        rule.addAntecedent(new EqualsClause("Your ukrainian language score", "170"));
        rule.addAntecedent(new EqualsClause("Was maths exam?", "v"));
        rule.addAntecedent(new EqualsClause("Your maths score", "170"));
        rule.addAntecedent(new EqualsClause("Was foreign language exam?", "yes"));
        rule.addAntecedent(new EqualsClause("Your foreign language score", "170"));
        rule.addAntecedent(new EqualsClause("University", "UMSF"));
        rule.setConsequent(new EqualsClause("Your chances:","average"));
        rie.addRule(rule);

        rule=new Rule("There is average chances getting on budget in CS (160)");
        rule.addAntecedent(new EqualsClause("Your ukrainian language score", "160"));
        rule.addAntecedent(new EqualsClause("Was maths exam?", "yes"));
        rule.addAntecedent(new EqualsClause("Your maths score", "160"));
        rule.addAntecedent(new EqualsClause("Was foreign language exam?", "yes"));
        rule.addAntecedent(new EqualsClause("Your foreign language score", "160"));
        rule.addAntecedent(new EqualsClause("University", "UMSF"));
        rule.setConsequent(new EqualsClause("Your chances:","average"));
        rie.addRule(rule);

        rule=new Rule("There is less than average chances getting on budget in CS (150)");
        rule.addAntecedent(new EqualsClause("Your ukrainian language score", "150"));
        rule.addAntecedent(new EqualsClause("Was maths exam?", "yes"));
        rule.addAntecedent(new EqualsClause("Your maths score", "150"));
        rule.addAntecedent(new EqualsClause("Was foreign language exam?", "yes"));
        rule.addAntecedent(new EqualsClause("Your foreign language score", "150"));
        rule.addAntecedent(new EqualsClause("University", "UMSF"));
        rule.setConsequent(new EqualsClause("Your chances:","less than average"));
        rie.addRule(rule);

        rule=new Rule("There is small chances getting on budget in CS (140)");
        rule.addAntecedent(new EqualsClause("Your ukrainian language score", "140"));
        rule.addAntecedent(new EqualsClause("Was maths exam?", "yes"));
        rule.addAntecedent(new EqualsClause("Your maths score", "140"));
        rule.addAntecedent(new EqualsClause("Was foreign language exam?", "yes"));
        rule.addAntecedent(new EqualsClause("Your foreign language score", "140"));
        rule.addAntecedent(new EqualsClause("University", "UMSF"));
        rule.setConsequent(new EqualsClause("Your chances:","small"));
        rie.addRule(rule);

        rule=new Rule("There is very small chances getting on budget in CS (130)");
        rule.addAntecedent(new EqualsClause("Your ukrainian language score", "130"));
        rule.addAntecedent(new EqualsClause("Was maths exam?", "yes"));
        rule.addAntecedent(new EqualsClause("Your maths score", "130"));
        rule.addAntecedent(new EqualsClause("Was foreign language exam?", "yes"));
        rule.addAntecedent(new EqualsClause("Your foreign language score", "130"));
        rule.addAntecedent(new EqualsClause("University", "UMSF"));
        rule.setConsequent(new EqualsClause("Your chances:","very small"));
        rie.addRule(rule);

        rule=new Rule("There is very small chances getting on budget in CS (120)");
        rule.addAntecedent(new EqualsClause("Your ukrainian language score", "120"));
        rule.addAntecedent(new EqualsClause("Was maths exam?", "yes"));
        rule.addAntecedent(new EqualsClause("Your maths score", "120"));
        rule.addAntecedent(new EqualsClause("Was foreign language exam?", "yes"));
        rule.addAntecedent(new EqualsClause("Your foreign language score", "120"));
        rule.addAntecedent(new EqualsClause("University", "UMSF"));
        rule.setConsequent(new EqualsClause("Your chances:"," very small"));
        rie.addRule(rule);

        rule=new Rule("There is very small chances getting on budget in CS (min)");
        rule.addAntecedent(new EqualsClause("Your ukrainian language score", "100"));
        rule.addAntecedent(new EqualsClause("Was maths exam?", "yes"));
        rule.addAntecedent(new EqualsClause("Your maths score", "100"));
        rule.addAntecedent(new EqualsClause("Was foreign language exam?", "yes"));
        rule.addAntecedent(new EqualsClause("Your foreign language score", "100"));
        rule.addAntecedent(new EqualsClause("University", "UMSF"));
        rule.setConsequent(new EqualsClause("Your chances:","very small"));
        rie.addRule(rule);

        return rie;
    }
}
