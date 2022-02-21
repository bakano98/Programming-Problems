// added more comments for easy reading

class Team implements Comparable<Team> {
    public int team_number;
    public int total_solved;
    public long total_penalty;

    public Team(int team_number, int total_solved, long total_penalty) {
        this.team_number = team_number;
        this.total_solved = total_solved;
        this.total_penalty = total_penalty;
    }

    public Team() {
    };

    // updates the team's penalty and # of problems solved
    public void update(long penalty) {
        this.total_solved++;
        this.total_penalty = this.total_penalty + penalty;
    }

    // -1 -> lower ranking
    // 0 -> same team
    // 1 -> higher ranking
    public int compareTo(Team b) {
        if (this.total_solved > b.total_solved) {
            return 1;
        } else if (this.total_solved < b.total_solved) {
            return -1;
        } else {
            // same number of problems solved
            if (this.total_penalty < b.total_penalty) {
                // this team has lesser penalty, so higher ranking
                return 1;
            } else if (this.total_penalty > b.total_penalty) {
                // more penalty, lower ranking
                return -1;
            } else {
                if (this.team_number < b.team_number) {
                    // team number is smaller -- so prioritise their ranking
                    return 1;
                } else if (this.team_number > b.team_number) {
                    // team number is bigger, so prioritise the other's ranking
                    return -1;
                } else {
                    // same team, just return 0
                    return 0;
                }
            }
        }
    }
}