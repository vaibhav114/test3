import java.util.*;

class Process{
    int pid;
    int at;
    int bt;
    int p;
    int st;
    int ct;
    int tat;
    int wt;
}
public class npp {
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        Process p[] = new Process[10000];
        int total_tat=0;
        int total_wt=0;
        float avg_tat;
        float avg_wt;
        boolean completed_a[]= new boolean[100];

        System.out.println("Enter Number of Process : ");
        int n = sc.nextInt();

        for(int i=0;i<n;i++)
        {
            p[i] = new Process();
            System.out.println("Enter Arrival Time : ");
            p[i].at=sc.nextInt();
            System.out.println("Enter Burst Time : ");
            p[i].bt=sc.nextInt();
            System.out.println("Enter Prioirty : ");
            p[i].p=sc.nextInt();
            p[i].pid=i+1;
            System.out.println();
        }

        for(int i=0;i<n;i++)
        {
            completed_a[i]=false;
        }
        int completed=0;
        int curr=0;
        while(completed != n)
        {
            int idx=-1;
            int maxP =-1;
            for(int i=0;i<n;i++)
            {
                if(p[i].at<=curr && !completed_a[i])
                {
                    if(p[i].p > maxP)
                    {
                        maxP=p[i].p;
                        idx=i;
                    }
                    if(p[i].p==maxP)
                    {
                        if(p[i].at < p[idx].at)
                        {
                            maxP=p[i].p;
                            idx=i;
                        }
                    }
                }
            }
            if(idx != -1)
            {
                p[idx].st=curr;
                p[idx].ct=p[idx].st+p[idx].bt;
                p[idx].tat=p[idx].ct-p[idx].at;
                p[idx].wt=p[idx].tat-p[idx].bt;
                total_tat=total_tat+p[idx].tat;
                total_wt=total_wt+p[idx].wt;
                completed_a[idx]=true;
                completed++;
                curr = p[idx].ct;
            }
            else{
                curr++;
            }
        }
        avg_tat=(float) total_tat/n;
        avg_wt=(float) total_wt/n;
        System.out.println("AVG TAT : "+avg_tat);
        System.out.println("AVG WT  : "+avg_wt);
    }   
}
