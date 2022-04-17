package cn.zry551.plugin.huneiand.UDP;

import cn.zry551.plugin.huneiand.Huneiand;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TickRun extends BukkitRunnable {
    public Huneiand HP;
    public List<String> Tasks = new ArrayList<>();

    public TickRun(Huneiand huneiand) {
        this.HP = huneiand;
    }
    //java.util.ConcurrentModificationException
    @Override
    public void run() {
        /*for (String MSG : Tasks) {
            try {
                DataSyn.run(HP,MSG);
                Tasks.remove(MSG);
            } catch (Exception ex) {
                ex.printStackTrace();

            }
        }*/
        for(Iterator<String> it = Tasks.iterator(); it.hasNext();) {
            String MSG = it.next();
            try {
                DataSyn.run(HP,MSG);
                it.remove();
            } catch (Exception ex) {
                ex.printStackTrace();

            }

        }
    }
}
