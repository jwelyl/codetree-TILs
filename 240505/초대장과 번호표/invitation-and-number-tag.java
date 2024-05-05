import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int g = sc.nextInt();

        Map<Integer, List<Integer>> groupsByPerson = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            groupsByPerson.put(i, new ArrayList<>());
        }

        Map<Integer, Set<Integer>> notInvitedPeopleByGroup = new HashMap<>();
        for (int i = 1; i <= g; i++) {
            notInvitedPeopleByGroup.put(i, new HashSet<>());
        }

        for (int i = 1; i <= g; i++) {
            int k = sc.nextInt();
            for (int j = 0; j < k; j++) {
                int person = sc.nextInt();

                List<Integer> group = groupsByPerson.get(person);
                group.add(i);

                Set<Integer> notInvitedPeople = notInvitedPeopleByGroup.get(i);
                notInvitedPeople.add(person);
            }
        }

        int answer = 0;
        boolean[] invited = new boolean[n + 1];
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        invited[1] = true;

        while (!q.isEmpty()) {
            int person = q.poll();
            answer++;

            List<Integer> group = groupsByPerson.get(person);
            for (int groupNumber : group) {
                Set<Integer> notInvitedPeople = notInvitedPeopleByGroup.get(groupNumber);
                notInvitedPeople.remove(person);

                if (notInvitedPeople.size() == 1) {
                    int remainPerson = new ArrayList<>(notInvitedPeople).get(0);
                    if (!invited[remainPerson]) {
                        q.add(remainPerson);
                        invited[remainPerson] = true;
                    }
                }
            }
        }

        System.out.print(answer);
    }
}