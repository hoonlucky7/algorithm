/*
https://school.programmers.co.kr/learn/courses/30/lessons/42576

문제 설명
수많은 마라톤 선수들이 마라톤에 참여하였습니다. 단 한 명의 선수를 제외하고는 모든 선수가 마라톤을 완주하였습니다.

마라톤에 참여한 선수들의 이름이 담긴 배열 participant와 완주한 선수들의 이름이 담긴 배열 completion이 주어질 때,
완주하지 못한 선수의 이름을 return 하도록 solution 함수를 작성해주세요.

제한사항
마라톤 경기에 참여한 선수의 수는 1명 이상 100,000명 이하입니다.
completion의 길이는 participant의 길이보다 1 작습니다.
참가자의 이름은 1개 이상 20개 이하의 알파벳 소문자로 이루어져 있습니다.
참가자 중에는 동명이인이 있을 수 있습니다.
입출력 예
participant	completion	return
["leo", "kiki", "eden"]	["eden", "kiki"]	"leo"
["marina", "josipa", "nikola", "vinko", "filipa"]	["josipa", "filipa", "marina", "nikola"]	"vinko"
["mislav", "stanko", "mislav", "ana"]	["stanko", "ana", "mislav"]	"mislav"
입출력 예 설명
예제 #1
"leo"는 참여자 명단에는 있지만, 완주자 명단에는 없기 때문에 완주하지 못했습니다.

예제 #2
"vinko"는 참여자 명단에는 있지만, 완주자 명단에는 없기 때문에 완주하지 못했습니다.

예제 #3
"mislav"는 참여자 명단에는 두 명이 있지만, 완주자 명단에는 한 명밖에 없기 때문에 한명은 완주하지 못했습니다.



*/
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
[solution 1] : HashMap이용
1) 참가자 이름과 개수를 map에 기록
2) 완주한 이름의 개수를 map에 감소
3) count가 1 이상인 참가자를 찾음

시간 복잡도 : O(N * logN)
공간 복잡도 : O(N)

[solution 2] : sort를 이용
1) 참가자 이름 sort
2) 완주한 이름 sort
3) index로 비교해서 참가자 이름과 완주한 이름이 다른 경우 그 참가자 이름이 완주하지 못하 이름

시간 복잡도 : O(N * logN)
공간 복잡도 : O(N)

 */

public class IncompleteRunner {

    /*public String solution(String[] participant, String[] completion) {

        Map<String, Integer> participantCountMap = new HashMap<>();
        //1) 참가자 이름과 개수를 기록
        for (String name : participant) {
            participantCountMap.put(name, participantCountMap.getOrDefault(name, 0) + 1);
        }

        //2) 완주한 이름의 개수를 감소
        for (String name : completion) {
            participantCountMap.put(name, participantCountMap.getOrDefault(name, 0) - 1);
        }

        //3) count가 1 이상인 참가자를 찾음
        for (String name : participant) {
            if (participantCountMap.getOrDefault(name, 0) >= 1) {
                return name;
            }
        }

        return ""; // 미완주자가 없는 경우 (비정상적인 상황);
    }*/

    public String solution(String[] participant, String[] completion) {
        Arrays.sort(participant);
        Arrays.sort(completion);

        for (int i = 0; i < participant.length; i++) {
            if (i == participant.length - 1 || !participant[i].equals(completion[i])) {
                return participant[i];
            }
        }
        return "";
    }

    public static void main(String[] args) {
        IncompleteRunner incompleteRunner = new IncompleteRunner();
        String[] participant1 = new String[]{"leo", "kiki", "eden"};
        String[] completion1 = new String[]{"eden", "kiki"};
        System.out.println(incompleteRunner.solution(participant1, completion1));
        String[] participant2 = new String[]{"marina", "josipa", "nikola", "vinko", "filipa"};
        String[] completion2 = new String[]{"josipa", "filipa", "marina", "nikola"};
        System.out.println(incompleteRunner.solution(participant2, completion2));
        String[] participant3 = new String[]{"mislav", "stanko", "mislav", "ana"};
        String[] completion3 = new String[]{"stanko", "ana", "mislav"};
        System.out.println(incompleteRunner.solution(participant3, completion3));
    }
}

/*

C로 구현
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define TABLE_SIZE 1000 // 해시 테이블 크기

// 해시 테이블 엔트리 구조체
typedef struct HashNode {
    char name[21]; // 참가자 이름
    int count;     // 이름의 개수
    struct HashNode *next; // 체이닝을 위한 포인터
} HashNode;

// 해시 테이블
HashNode *hashTable[TABLE_SIZE];

// 해시 함수 (단순 가중치 합 방식)
unsigned int hash(const char *str) {
    unsigned int hash = 0;
    while (*str) {
        hash = (hash * 31) + (*str++); // 간단한 해시 계산
    }
    return hash % TABLE_SIZE;
}

// 해시 테이블에 삽입 또는 업데이트
void insertOrUpdate(const char *name, int delta) {
    unsigned int index = hash(name);
    HashNode *node = hashTable[index];

    // 체이닝을 통해 이름 검색
    while (node) {
        if (strcmp(node->name, name) == 0) {
            node->count += delta;
            return;
        }
        node = node->next;
    }

    // 이름이 없으면 새 노드 생성
    HashNode *newNode = (HashNode *)malloc(sizeof(HashNode));
    strcpy(newNode->name, name);
    newNode->count = delta;
    newNode->next = hashTable[index];
    hashTable[index] = newNode;
}

// 해시 테이블에서 값 검색
int get(const char *name) {
    unsigned int index = hash(name);
    HashNode *node = hashTable[index];

    while (node) {
        if (strcmp(node->name, name) == 0) {
            return node->count;
        }
        node = node->next;
    }

    return 0; // 이름이 없으면 0 반환
}

// 메모리 해제
void freeHashTable() {
    for (int i = 0; i < TABLE_SIZE; i++) {
        HashNode *node = hashTable[i];
        while (node) {
            HashNode *temp = node;
            node = node->next;
            free(temp);
        }
    }
}

// solution 함수
const char* solution(char *participant[], int participantSize, char *completion[], int completionSize) {
    static char answer[21]; // 반환할 이름 저장

    // 1) 참가자 이름과 개수를 해시 테이블에 기록
    for (int i = 0; i < participantSize; i++) {
        insertOrUpdate(participant[i], 1);
    }

    // 2) 완주한 이름의 개수를 감소
    for (int i = 0; i < completionSize; i++) {
        insertOrUpdate(completion[i], -1);
    }

    // 3) count가 1 이상인 참가자를 찾음
    for (int i = 0; i < participantSize; i++) {
        if (get(participant[i]) > 0) {
            strcpy(answer, participant[i]);
            break;
        }
    }

    // 해시 테이블 메모리 해제
    freeHashTable();

    return answer;
}

// 테스트
int main() {
    char *participant[] = {"leo", "kiki", "eden"};
    char *completion[] = {"eden", "kiki"};
    int participantSize = 3;
    int completionSize = 2;

    const char *result = solution(participant, participantSize, completion, completionSize);
    printf("완주하지 못한 사람: %s\n", result);

    return 0;
}
 */