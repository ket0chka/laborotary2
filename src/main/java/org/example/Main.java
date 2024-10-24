package org.example;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;

public class Main {
    public static void main(String[] args) {
        // Получаем экземпляр JADE Runtime
        Runtime rt = Runtime.instance();

        // Создаем профиль JADE
        Profile p = new ProfileImpl();
        p.setParameter(Profile.MAIN_HOST, "localhost");
        p.setParameter(Profile.MAIN_PORT, "1099");
        p.setParameter(Profile.GUI, "true");

        // Создаем основной контейнер
        ContainerController cc = rt.createMainContainer(p);

        try {
            // Создаем и запускаем агентов
            AgentController agent1 = cc.createNewAgent("agent1", "org.example.FunctionAgent", new Object[]{"agent2", "agent3"});
            AgentController agent2 = cc.createNewAgent("agent2", "org.example.FunctionAgent", new Object[]{"agent1", "agent3"});
            AgentController agent3 = cc.createNewAgent("agent3", "org.example.FunctionAgent", new Object[]{"agent1", "agent2"});

            agent1.start();
            agent2.start();
            agent3.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}