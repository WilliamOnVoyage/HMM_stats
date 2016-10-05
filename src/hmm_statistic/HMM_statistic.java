/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hmm_statistic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author Moliang
 */
public class HMM_statistic {

    private List<EventTrace> evaluation_alignment;
    private int[] stats;

    private int[] strength_stats;

    /*
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        HMM_statistic hmm = new HMM_statistic();
        System.out.println("--------------------------------------------");
        hmm.Read_alignment("AIM2_DA.csv");
        hmm.Calculate_transition();
        hmm.Calculate_transitionStrength();
        hmm.print_stats(hmm.strength_stats);

        System.out.println("--------------------------------------------");
        hmm.Read_alignment("AIM2_NW.csv");
        hmm.Calculate_transition();
        hmm.Calculate_transitionStrength();
        hmm.print_stats(hmm.strength_stats);

        System.out.println("--------------------------------------------");
        hmm.Read_alignment("Intubation_DA.csv");
        hmm.Calculate_transition();
        hmm.Calculate_transitionStrength();
        hmm.print_stats(hmm.strength_stats);

        System.out.println("--------------------------------------------");
        hmm.Read_alignment("Intubation_NW.csv");
        hmm.Calculate_transition();
        hmm.Calculate_transitionStrength();
        hmm.print_stats(hmm.strength_stats);
    }

    public void Read_alignment(String address) {

        ReadAlignment rd = new ReadAlignment(address);
        try {
            evaluation_alignment = rd.Read_alignment();
            stats = new int[evaluation_alignment.get(0).events.size()];
            strength_stats = new int[evaluation_alignment.size()];
        } catch (ActivityOverFlowException ex) {
            System.out.println("Read alignment ERROR!");
        }

    }

    private void Calculate_transitionStrength() {
        int state_index = 0;
        while (state_index < evaluation_alignment.get(0).getTraceLength()) {
            HashMap<String, Integer> trans_strength = new HashMap<>();
            for (EventTrace t : evaluation_alignment) {
                String this_ac = t.events.get(state_index).getActivity();
                if (this_ac.equals("-")) {
                    continue;
                }
                for (int i = state_index + 1; i < t.getTraceLength(); i++) {
                    String ac = t.events.get(i).getActivity();
                    if (!ac.equals("-")) {
                        if (!trans_strength.containsKey(ac)) {
                            trans_strength.put(ac, 1);
                        } else {
                            trans_strength.put(ac, trans_strength.get(ac) + 1);
                        }
                        break;
                    }
                }
            }

            List<Integer> map_val = new ArrayList<>(trans_strength.values());
            Collections.sort(map_val);
            Collections.reverse(map_val);
//            for (int i = 0; i < map_val.size(); i++) {
//                if (map_val.get(i) < map_val.get(0)) {
//                    break;
//                }
//                strength_stats[map_val.get(i)]++;
//            }
            if (map_val.size() > 0) {
                strength_stats[map_val.get(0)]++;
            }
            state_index++;
        }
    }

    private void Calculate_transition() {
        int state_index = 0;
        while (state_index < evaluation_alignment.get(0).getTraceLength()) {
            HashSet<String> trans_states = new HashSet<>();
            for (EventTrace t : evaluation_alignment) {
                String this_ac = t.events.get(state_index).getActivity();
                if (this_ac.equals("-")) {
                    continue;
                }
                for (int i = state_index + 1; i < t.getTraceLength(); i++) {
                    String ac = t.events.get(i).getActivity();
                    if (!ac.equals("-")) {
                        if (!trans_states.contains(ac)) {
                            trans_states.add(ac);
                            stats[state_index]++;
                        }
                        break;
                    }
                }
            }
            state_index++;
        }
    }

    public void print_stats(int[] stats) {
        for (int i : stats) {
            System.out.print(i + ", ");
        }
        System.out.println();
    }

    public int[] get_stats() {
        return this.stats;
    }

    public int[] get_strength_stats() {
        return this.strength_stats;
    }

}
