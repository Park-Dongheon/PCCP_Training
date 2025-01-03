package pccp시험_20241123;

import java.util.List;
import java.util.ArrayList;

/**
 * ArrowGame_Refactoring 클래스는 화살표 방향을 맞추는 게임에서 벌점을 최소화하기 위한 버튼 누르는 최적의 위치를 찾는 기능을 제공합니다.
 */
public class 문제1_ArrowGame_Refactoring {

	/**
	 * 최소 벌점을 구하고, 그때 버튼을 눌러야 하는 위치를 반환합니다.
	 * 버튼을 누르지 않는 것이 최적일 경우 -1을 반환합니다.
	 *
	 * @param arrows 화살표 방향을 나타내는 정수 배열
	 * @return 최소 벌점을 만들기 위한 버튼 누르는 위치
	 * @throws IllegalArgumentException arrows 가 null 이거나 길이가 1 미만일 경우
	 */
	public int findOptimalButtonPressPosition(int[] arrows) {
		if (arrows == null || arrows.length == 0) {
			throw new IllegalArgumentException("arrows 배열은 null이 아니고, 최소 1개의 요소를 가져야 합니다.");
		}
		
		for (int i = 0; i < arrows.length; i++) {
			if (arrows[i] != 0 && arrows[i] != 1) {
				throw new IllegalArgumentException("arrows 배열의 모든 요소는 0 또는 1이어야 합니다.");
			}
		}
		
		int numberOfArrows = arrows.length;
		int minPenalty = calculatePenalty(arrows, numberOfArrows + 1);	// 버튼을 누르지 않는 경우
		List<Integer> optimalPositions = new ArrayList<>();
		
		//버튼을 누르지 않는 경우
		optimalPositions.add(numberOfArrows + 1);
		
		// 버튼을 누르는 모든 위치 계산
		for (int pressPosition  = 1; pressPosition  <= numberOfArrows + 1; pressPosition ++) {
			int penalty = calculatePenalty(arrows, pressPosition );
			
			if (penalty < minPenalty) {
				minPenalty = penalty;
				optimalPositions.clear();
				optimalPositions.add(pressPosition );
			} else if (penalty == minPenalty) {
				optimalPositions.add(pressPosition );
			}
			
		}
		
		// 버튼을 누르지 않는 경우가 최소 벌점인 경우
		if (optimalPositions.contains(numberOfArrows + 1) ) {
			return -1;
		}
		
		// 최소 벌점을 만드는 버튼 위치 중 가장 빠른 위치 반환
		return optimalPositions.stream().min(Integer::compareTo).orElse(-1);
	}
	
	/**
	 * 특정 위치에서 버튼을 눌렀을 때의 벌점을 계산합니다.
	 *
	 * @param arrows        화살표 방향 배열 (0: 위, 1: 아래)
	 * @param pressPosition 버튼을 누르는 위치 (1부터 n까지), 누르지 않을 경우 n + 1
	 * @return 계산된 벌점
	 */
	private int calculatePenalty(int[] arrows, int pressPosition) {
		int penalty = 0;
		int currentDirection = 0;	// 초기 방향은 위(0)
		int consecutiveMistakes = 0;
		
		for (int i = 1; i <= arrows.length; i++) {
			// 버튼을 누르는 시점에 도달하면 방향을 변경
			if (i == pressPosition) {
				currentDirection = 1 - currentDirection;
			}
			
			if (currentDirection == arrows[i - 1]) {
				// 방향이 맞으면 연속 틀린 횟수를 초기화
				consecutiveMistakes = 0;
			} else {
				// 방향이 틀리면 연속 틀린 횟수를 증가시키고 벌점을 추가
				consecutiveMistakes += 1;
				penalty += consecutiveMistakes;
			}
		}
		
		return penalty;
	}
	
    /**
     * 다양한 테스트 케이스를 실행하여 솔루션을 검증합니다.
     */
	public void runTests() {
		List<TestCase> testCases = List.of(
				new TestCase(new int[] {0, 0, 1, 1, 1, 0}, 3),
				new TestCase(new int[] {0, 0, 0, 0, 0}, -1),
				new TestCase(new int[] {0, 0, 0, 0, 1, 0, 1, 1}, 5),
				new TestCase(new int[] {1, 1, 1, 0, 0, 1, 0, 1, 0, 0}, 1),
				new TestCase(new int[] {1, 1, 1, 0, 0, 0}, -1),
		        // 추가 테스트 케이스
		        new TestCase(new int[] {0}, -1),
		        new TestCase(new int[] {1, 0, 1, 0}, 1),
		        new TestCase(new int[] {0, 1, 0, 1, 0, 1}, -1),
		        // 경계 조건 테스트 케이스
		        new TestCase(new int[] {1, 1, 1, 1, 1, 1}, -1),
		        new TestCase(new int[] {1, 0, 0, 1, 0, 1, 0, 1, 0, 1}, 1)
		);
		
		for (int i = 0; i < testCases.size(); i++) {
			TestCase tc = testCases.get(i);
			try {
				int result = findOptimalButtonPressPosition(tc.arrows);
				if (tc.expectedResult == result) {
					System.out.printf("Test Case %d: Expected = %d, Actual = %d => PASS%n",
							i + 1,
							tc.expectedResult,
							result);					
				} else {
					System.out.printf("Test Case %d: Expected = %d, Actual = %d => FAIL%n",
							i + 1,
							tc.expectedResult,
							result);					
				}
			} catch (Exception e) {
				System.out.printf("Test Case %d: Threw an exception: %s => FAIL%n", i + 1, e.getMessage());
			}
		}
	}
	
    /**
     * 테스트 케이스를 나타내는 내부 클래스입니다.
     */
	private static class TestCase {
		int[] arrows;
		int expectedResult;
		
		TestCase(int[] arrows, int expectedResult) {
			this.arrows = arrows;
			this.expectedResult = expectedResult;
		}
	}
	
	public static void main(String[] args) {
		문제1_ArrowGame_Refactoring s = new 문제1_ArrowGame_Refactoring();
		s.runTests();
	}
	
}
