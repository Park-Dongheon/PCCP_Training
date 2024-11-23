package pccp시험_20241123;

/*
문제 설명:
당신은 화살표 방향을 맞추는 게임을 합니다. 위 또는 아래를 가리키는 화살표가 하나씩 입력됩니다. 입력된 화살표와 당신의 화살표 방향이 같다면 통과하지만 다르다면 연속해서 방향을 틀린 횟수만큼 벌점을 받습니다. 당신의 화살표는 게임을 시작할 때 위를 가리키고 있습니다. 원하는 타이밍에 버튼을 눌러 당신이 가진 화살표의 방향을 딱 한 번만 바꿀 수 있으며, 버튼을 누르지 않을 수도 있습니다.
	↑↑↓↓↓↑
  You →	↑↑↑↑↑↑
- 왼쪽부터 순서대로 위, 위, 아래, 아래, 아래, 위 방향으로 화살표 6개가 입력될 때 버튼을 누르지 않으면 3, 4, 5 번째 화살표를 틀리고 받는 벌점은 1 + 2 + 3 = 6 점 입니다.
	↑↑↓↓↓↑
  You →	↑↑↓↓↓↓
- 만약 3번째 화살표가 입력될 때 버튼을 눌러 화살표의 방향을 바꾸면, 마지막 화살표 입력만 틀려 벌점을 1점 받습니다. 이보다 더 작은 벌점을 받는 방법은 없습니다. 입력될 n 개의 화살표 방향을 나타내는 1차원 정수 배열 arrows 가 매개변수로 주어집니다. 이때 벌점을 최소로 만들기 위해 몇 번째 화살표가 입력될 때 방향을 바꿔야 하는지 return 하도록 solution 함수를 완성해 주세요.
만약 화살표 방향을 바꾸지 않을 때 벌점이 최소라면 -1을, 벌점을 최소로 만드는 방법이 여러 개라면 가장 빠른 번호를 return 해주세요.

제한 사항:
- 1 <= arrows 의 길이 = n <= 100
	- arrows[i]는 i + 1 번째 화살표의 방향을 의미합니다.
	- arrows[i] 는 0 또는 1 입니다.
	- 0은 위를 가리키는 화살표를 나타냅니다.
	- 1은 아래를 가리키는 화살표를 나타냅니다.

입출력 예:
	arrows			result
	[0, 0, 1, 1, 1, 0]		3
	[0, 0, 0, 0, 0]		-1
	[0, 0, 0, 0, 1, 0, 1, 1]	5
	[1, 1, 1, 0, 0, 1, 0, 1, 0, 0]	1
	[1, 1, 1, 0, 0, 0]		-1

입출력 예 설명:
	입출력 예 #1: 문제 예시와 동일합니다.
	입출력 예 #2: 버튼을 누르지 않았을 때 벌점이 0점으로 가장 작습니다. 따라서 -1을 return 해야 합니다.
	입출력 예 #3: 5번째, 또는 7번째 화살표가 입력될 때 버튼을 누르면 벌점이 1점으로 가장 작습니다. 둘 중 더 		      빠른 5를 return 해야 합니다.
	입출력 예 #4: 첫 번째 화살표가 입력될 때 버튼을 누르면 벌점이 7점으로 가장 작습니다. 따라서 1을 return 		      해야 합니다.
	입출력 예 #5: 버튼을 누르지 않았을 때, 첫 번째 화살표가 입력될 때 버튼을 눌렀을 때 벌점이 6점으로 가장 		      작습니다. 화살표 방향을 바꾸지 않았을 때 벌점이 최소이므로 -1을 return 해야 합니다.
*/
import java.util.List;
import java.util.ArrayList;

public class 문제1_ArrowGame {
	
    /**
     * 최소 벌점을 구하고, 그때 버튼을 눌러야 하는 위치를 반환합니다.
     * 버튼을 누르지 않는 것이 최적일 경우 -1을 반환합니다.
     *
     * @param arrows 화살표 방향을 나타내는 정수 배열
     * @return 최소 벌점을 만들기 위한 버튼 누르는 위치
     */
	public int solution(int[] arrows) {
		int n = arrows.length;
		int minPenalty = Integer.MAX_VALUE;
		// 저장할 최소 벌점을 만드는 버튼 위치
		// 버튼을 누르지 않은 경우는 p = n + 1로 표시
		// 사용 가능한 모든 버튼 누르는 위치는 1부터 n까지
		// p = n + 1은 버튼을 누르지 않는 경우
		// minPosition은 최소 벌점을 만드는 모든 버튼 위치를 저장
		List<Integer> minPositions = new ArrayList<>();
		
		// p 는 버튼을 누르는 위치를 나타냅니다.
		// 1부터 n 까지는 각각의 화살표 전에 버튼을 누르는 경우
		// n + 1은 버튼을 누르지 않는 경우
		for (int p = 1; p <= n + 1; p++) {
			int penalty = 0;
			int direction = 0;		// 초기 방향은 위(0)
			int wrongStreak = 0;	// 연속해서 틀린 횟수
			
			for (int i = 1; i <= n; i++) {
				// 버튼을 누르는 시점에 도달하면 방향을 변경
				if (i == p && p <= n) {
					direction = 1 - direction;
				}
				
				if (direction == arrows[i - 1]) {
					// 방향이 맞으면 연속 틀린 횟수를 초기화
					wrongStreak = 0;
				} else {
					// 방향이 틀리면 연속 틀린 횟수를 증가시키고 벌점을 추가
					wrongStreak += 1;
					penalty += wrongStreak;
				}
			}
			
			// 최소 벌점을 갱신하거나 동일한 벌점인 경우 위치를 추가
			if (penalty < minPenalty) {
				minPenalty = penalty;
				minPositions.clear();
				minPositions.add(p);
			}
			else if (penalty == minPenalty) {
				minPositions.add(p);
			}
		}
		
		// 버튼을 누르지 않는 경우가 최소 벌점 중 하나인지 확인
		boolean noPressMin = false;
		for (int p : minPositions) {
			if (p == n + 1) {
				noPressMin = true;
				break;
			}
		}
		if (noPressMin) {
			return -1;
		}
		
		// 최소 벌점을 만드는 버튼 위치 중 가장 빠른 위치를 반환
		int earliestP = Integer.MAX_VALUE;
		for (int p : minPositions) {
			if (p < earliestP) {
				earliestP = p;
			}
		}
		return earliestP;
		
	}
	
	// 테스트 케이스를 실행하는 메인 메소드
	public static void main(String[] args) {
		문제1_ArrowGame s = new 문제1_ArrowGame();
		
		// Test Case 1
		int[] arrows1 = {0, 0, 1, 1, 1, 0};
		System.out.println(s.solution(arrows1));	// 예상 출력: 3
		
		// Test Case 2
		int[] arrows2 = {0, 0, 0, 0, 0};
		System.out.println(s.solution(arrows2));	// 예상 출력: -1
		
		// Test Case 3
		int[] arrows3 = {0, 0, 0, 0, 1, 0, 1, 1};
		System.out.println(s.solution(arrows3));	// 예상 출력: 5
		
		// Test Case 4
		int[] arrows4 = {1, 1, 1, 0, 0, 1, 0, 1, 0, 0};
		System.out.println(s.solution(arrows4));	// 예상 출력: 1
		
		// Test Case 5
		int[] arrows5 = {1, 1, 1, 0, 0, 0};
		System.out.println(s.solution(arrows5));	// 예상 출력: -1
	}
	
}
