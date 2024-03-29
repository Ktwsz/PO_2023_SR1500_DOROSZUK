package agh.ics.oop;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SimulationEngine {

    private final List <Simulation> simulationList;
    private final List <Thread> simulationThreads = new ArrayList<>();
    private final ExecutorService executorService = Executors.newFixedThreadPool(4);
    public SimulationEngine(List<Simulation> simulationList) {
        this.simulationList = simulationList;

        for (Simulation sim : simulationList) simulationThreads.add(new Thread(sim));
    }

    public SimulationEngine() {
        this(new ArrayList<Simulation>());
    }

    public void addAndRun(Simulation simulation) {
        simulationList.add(simulation);
        simulationThreads.add(new Thread(simulation));
        simulationThreads.get(simulationThreads.size() - 1).start();
    }

    public void runSync() {
        for (Simulation sim : simulationList) sim.run();
    }

    public void runAsync() {
        for (Thread thread : simulationThreads) thread.start();
    }

    public void awaitSimulationsEnd() {
        for (Thread thread : simulationThreads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        executorService.shutdown();
        try {
            executorService.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void runAsyncInThreadPool() {
        for (Simulation sim : simulationList) executorService.submit(sim);
    }
}
